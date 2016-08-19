package com.github.emailtohl.web.wechat.util;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.github.emailtohl.web.wechat.boot.SpringUtil;
import com.github.emailtohl.web.wechat.config.Constant;
import com.github.emailtohl.web.wechat.domain.menu.Button;
import com.github.emailtohl.web.wechat.domain.menu.ClickButton;
import com.github.emailtohl.web.wechat.domain.menu.ComplexButton;
import com.github.emailtohl.web.wechat.domain.menu.Menu;
import com.github.emailtohl.web.wechat.domain.menu.ViewButton;
import com.google.gson.Gson;
/**
 * 可用它生成菜单
 * @author HeLei
 */
public class MenuUtilTest {
	MenuUtil util;
	Gson gson;
	Environment env;

	@Before
	public void setUp() {
		AnnotationConfigApplicationContext ctx = SpringUtil.ctx;
		util = ctx.getBean(MenuUtil.class);
		gson = ctx.getBean(Gson.class);
		env = ctx.getBean(Environment.class);
	}

//	@Test
	public void testMenu() {
		boolean exist = true;
		String menuJson = util.getMenuJson();
		if (menuJson.contains("46003")) {
			exist = false;
		}
		if (exist) {
			System.err.println("原menu是：");
			System.err.println(menuJson);
			Assert.assertTrue(util.deleteMenu());
		}
		ClickButton btn11 = new ClickButton();
		btn11.setName("点击得到一个随机数");
		btn11.setType("click");
		btn11.setKey("random");

		ClickButton btn12 = new ClickButton();
		btn12.setName("点击得到一张图片");
		btn12.setType("click");
		btn12.setKey("picture");

		ViewButton btn21 = new ViewButton();
		btn21.setName("百度");
		btn21.setType("view");
		btn21.setUrl("http://www.baidu.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("淘宝");
		btn22.setType("view");
		btn22.setUrl("https://m.taobao.com/");

		ViewButton btn23 = new ViewButton();
		btn23.setName("网页授权");
		btn23.setType("view");
		String url = Constant.REDIRECT_URI_GET_CODE.replace("APPID", env.getProperty("appID"))
				.replace("REDIRECT_URI", env.getProperty("redirect_uri")).replace("SCOPE", env.getProperty("scope"))
				.replace("STATE", "yyy");
		btn23.setUrl(url);

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("点击有惊喜");
		mainBtn1.setSub_button(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("网站链接");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });
		Assert.assertTrue(util.createMenu(menu));

		menu = util.getMenu();
		System.out.println(gson.toJson(menu));
		System.out.println(util.getMenuJson());

		// Assert.assertTrue(util.deleteMenu());

		if (exist) {
			// Assert.assertTrue(util.createMenu(menuJson));
		}
	}
}
