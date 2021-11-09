package com.github.emailtohl.web.wechat.controller;

import com.github.emailtohl.web.wechat.domain.auth.SNSUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 直接获取JSP页面
 *
 * @author helei
 */
@Controller
@RequestMapping("test")
public class JspPageTest {

  @RequestMapping(value = "yyyUserPage", method = RequestMethod.GET)
  public String getYyyUserPage(Model model) {
    SNSUserInfo snsUserInfo = new SNSUserInfo();
    snsUserInfo.setCity("Chongqing");
    snsUserInfo.setCountry("CN");
    snsUserInfo.setHeadimgurl("http://wx.qlogo.cn/mmopen/1MLz0YkS76Hq4FmicnM7joJksdhbcYicuWILexCdV8eN0AibH75uib76G52Y5YJe96AicS9N5U0SZeINicY1hWR5F01A/0");
    snsUserInfo.setNickname("afang");
    snsUserInfo.setOpenid("osdhdt1gmpndxUh4bGRhfxsrTl3I");
    snsUserInfo.setProvince("South Bank");
    snsUserInfo.setSex(1);
    model.addAttribute("snsUserInfo", snsUserInfo);
    return "yyy/yyyUserPage";
  }
}
