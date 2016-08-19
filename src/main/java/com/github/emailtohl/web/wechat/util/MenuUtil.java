package com.github.emailtohl.web.wechat.util;

import static com.github.emailtohl.web.wechat.config.Constant.MENU_CREATE_URL;
import static com.github.emailtohl.web.wechat.config.Constant.MENU_DELETE_URL;
import static com.github.emailtohl.web.wechat.config.Constant.MENU_GET_URL;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.emailtohl.web.wechat.domain.auth.AccessToken;
import com.github.emailtohl.web.wechat.domain.menu.Menu;
import com.github.emailtohl.web.wechat.service.TokenService;
import com.google.gson.Gson;
/**
 * 自定义菜单工具类
 * @author HeLei
 */
@Component
public class MenuUtil {
	private static Logger log = LogManager.getLogger();
	@Inject
	private RestTemplate restTemplate;
	@Inject
	HttpsClient httpsClient;
	@Inject
	private TokenService accessTokenService;
	@Inject
	private Gson gson;
	
	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @return true成功 false失败
	 */
	public boolean createMenu(Menu menu) {
		boolean flag = false;
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		String menuJson = gson.toJson(menu);
		String result = httpsClient.post(url, menuJson);
		AccessToken at = gson.fromJson(result, AccessToken.class);
		if (null != at) {
			Integer errorCode = at.getErrcode();
			String errorMsg = at.getErrmsg();
			if (null == errorCode || 0 == errorCode) {
				flag = true;
			} else {
				flag = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return flag;
	}

	public boolean createMenu2(Menu menu) {
		boolean flag = false;
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		String menuJson = gson.toJson(menu);
		AccessToken at = restTemplate.postForObject(url, menuJson, AccessToken.class);
		if (null != at) {
			Integer errorCode = at.getErrcode();
			String errorMsg = at.getErrmsg();
			if (null == errorCode || 0 == errorCode) {
				flag = true;
			} else {
				flag = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return flag;
	}

	public boolean createMenu(String menuJson) {
		boolean flag = false;
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		AccessToken at = restTemplate.postForObject(url, menuJson, AccessToken.class);
		if (null != at) {
			Integer errorCode = at.getErrcode();
			String errorMsg = at.getErrmsg();
			if (null == errorCode || 0 == errorCode) {
				flag = true;
			} else {
				flag = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return flag;
	}

	class RespMenu {
		Menu menu;

		public Menu getMenu() {
			return menu;
		}

		public void setMenu(Menu menu) {
			this.menu = menu;
		}
	}

	/**
	 * 查询菜单 注意：Menu的属性是基类Button，所以接收的信息可能不全，建议使用原始的Json，根据需要进行解析
	 * 
	 * @return
	 */
	public Menu getMenu() {
		String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		RespMenu rm = restTemplate.getForObject(requestUrl, RespMenu.class);
		return rm.getMenu();
	}

	/**
	 * 返回的Menu以json格式
	 * 
	 * @return
	 */
	public String getMenuJson() {
		String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		return httpsClient.get(requestUrl);
	}

	/**
	 * 删除菜单
	 * 
	 * @return true成功 false失败
	 */
	public boolean deleteMenu() {
		boolean flag = true;
		String requestUrl = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
		ResponseEntity<Result> r = restTemplate.getForEntity(requestUrl, Result.class, new Object[] {});
		if (null != r) {
			Integer errorCode = r.getBody().getErrcode();
			String errorMsg = r.getBody().getErrmsg();
			if (errorCode == null || errorCode != 0) {
				flag = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return flag;
	}

}

class Result {
	int errcode;
	String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}