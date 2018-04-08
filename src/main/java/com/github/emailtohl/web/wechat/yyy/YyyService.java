package com.github.emailtohl.web.wechat.yyy;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.emailtohl.web.wechat.config.WebSocketConfigurator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 微信摇一摇功能的服务器，使用websocket技术
 * @author HeLei
 */
@ServerEndpoint(value = "/yyyService/{endpoint}", configurator = WebSocketConfigurator.class)
public class YyyService {
	private static final Logger logger = LogManager.getLogger();
	private static final Long PLAYING_TIME = 30000L;
	private static final Integer GOAL = 20;// 目标是100步，达到此数字，进入成绩统计
	private static Map<Session, SNSUserInfo> userMap = new ConcurrentHashMap<Session, SNSUserInfo>();
	private static Set<Session> monitorSet = new CopyOnWriteArraySet<Session>();
	private static int i = 0, j = 0;
	private static volatile boolean running;
	private static Integer currentRank = 0;
	private Session session;
	private HttpSession httpSession;
	private Gson gson;
	private Type mapType = new TypeToken<Map<String, String>>() {}.getType();

	@OnOpen
	public void onOpen(Session session, @PathParam("endpoint") String endpoint) {
		if ("user".equals(endpoint)) {
			synchronized (userMap) {
				i++;
				logger.info("用户加入，ID：" + session.getId() + "共有 " + i + " 个用户");
			}
		} else if ("monitor".equals(endpoint)) {
			synchronized (monitorSet) {
				monitorSet.add(session);
				j++;
				logger.info("监视器加入，ID：" + session.getId() + "共有 " + j + " 个页面");
			}
		}
		this.session = session;
		this.httpSession = WebSocketConfigurator.getExposedSession(session);
		
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(httpSession.getServletContext());
		gson = context.getBean(Gson.class);
		
		logger.info(this.session.getId());
		logger.info(this.httpSession.getId());
		logger.info(this.gson);
	}

	@OnMessage
	public void onMessage(Session session, String message, @PathParam("endpoint") String endpoint) throws IOException {
		if ("user".equals(endpoint)) {
			Map<String, String> msg = gson.fromJson(message, mapType);
			String action = msg.get("action");
			if ("register".equals(action)) {
				SNSUserInfo user = new SNSUserInfo();
				user.setOpenid(msg.get("openId"));
				user.setNickname(msg.get("nickname"));
				user.setHeadimgurl(msg.get("headimgurl"));
				try {
					user.setSex(Integer.parseInt(msg.get("sex")));
				} catch (NumberFormatException e) {
					logger.error("性别数字解析失败", e);
				}
				user.setCity(msg.get("city"));
				userMap.put(session, user);
				for (Session monitor : monitorSet) {// 告诉各个监视器新增一个用户
					monitor.getBasicRemote().sendText(message);
				}
			} else if ("running".equals(action) && running) {
				SNSUserInfo user = userMap.get(session);
				user.increase();
				user.action = "running";
				String notifymsg = gson.toJson(user);// 将跑一步的信息转成json
				for (Session monitor : monitorSet) {// 发送到各个监视器上
					try {
						monitor.getBasicRemote().sendText(notifymsg);
					} catch (IllegalStateException e) {
						logger.log(Level.ERROR, "TEXT_FULL_WRITING 不明原因", e);
					}
				}
				session.getBasicRemote().sendText(notifymsg);
			}
		} else if ("monitor".equals(endpoint)) {
			if ("start".equals(message)) {
				running = true;
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						running = false;
						SNSUserInfo[] users = calculateRank();
						String rankmsg = gson.toJson(users);
						String notifymsg = "{\"action\":\"ending\",\"rank\":" + rankmsg + "}";
						for (Session server : monitorSet) {// 发送到各个监视器上
							try {
								server.getBasicRemote().sendText(notifymsg);
							} catch (IOException e) {
								logger.error(e);
							}
						}
						for (Session s : userMap.keySet()) {// 通知各客户端
							try {
								s.getBasicRemote().sendText(notifymsg);
							} catch (IOException e) {
								logger.error(e);
							}
						}
						logger.info("比赛结束");
					}
				}, PLAYING_TIME);
				logger.info("比赛开始，时间： " + PLAYING_TIME + " 秒");
			}
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("endpoint") String endpoint) {
		if ("monitor".equals(endpoint)) {
			monitorSet.remove(session);
		} else if ("user".equals(endpoint)) {
			SNSUserInfo user = userMap.get(session);
			user.action = "exit";
			String notifymsg = gson.toJson(user);
			for (Session monitor : monitorSet) {// 告诉各个监视器，该用户已离开
				try {
					monitor.getBasicRemote().sendText(notifymsg);
				} catch (IOException e) {
					logger.error(e);
				}
			}
			userMap.remove(session);
		}
	}

	private SNSUserInfo[] calculateRank() {
		Collection<SNSUserInfo> col = userMap.values();
		SNSUserInfo[] users = new SNSUserInfo[col.size()];
		users = col.toArray(users);
		Arrays.sort(users);
		return users;
	}

	private Integer getRank() {
		synchronized (currentRank) {
			return ++currentRank;
		}
	}

	private class SNSUserInfo extends com.github.emailtohl.web.wechat.domain.auth.SNSUserInfo
			implements Comparable<SNSUserInfo> {
		private static final long serialVersionUID = -5258711793811170384L;
		@SuppressWarnings("unused")
		private String action;
		private Integer rank;
		private int count = 0;

		void increase() {
			if (count >= GOAL) {
				if (rank == null) {
					rank = YyyService.this.getRank();
				}
			} else {
				count++;
			}
		}

		@Override
		public int compareTo(SNSUserInfo o) {// 名次排名为倒序
			if (rank != null) {
				if (o.rank != null) {
					return o.rank - rank;
				} else {
					return -1;
				}
			} else {
				if (o.rank != null) {
					return 1;
				} else {// 如果都没跑到终点，则计算他们的步数
					return o.count - count;
				}
			}
		}

	}
}
