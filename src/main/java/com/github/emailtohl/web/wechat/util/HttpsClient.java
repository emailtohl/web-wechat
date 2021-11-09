package com.github.emailtohl.web.wechat.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * HttpClient关于微信应用的封装
 *
 * @author helei
 * @date 2016.06.09
 */
@Component
public class HttpsClient {
  private final static Logger logger = LogManager.getLogger();
  @Autowired
  @Qualifier("acceptsUntrustedCertsHttpClient")
  private HttpClient httpClient;

  /**
   * 模拟浏览器post提交
   *
   * @param url
   * @return
   */
  public HttpPost getHttpPost(String url) {
    HttpPost pmethod = new HttpPost(url); // 设置响应头信息
    pmethod.addHeader("Connection", "keep-alive");
    pmethod.addHeader("Accept", "*/*");
    pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    pmethod.addHeader("Host", "mp.weixin.qq.com");
    pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
    pmethod.addHeader("Cache-Control", "max-age=0");
    pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    return pmethod;
  }

  /**
   * 模拟浏览器GET提交
   *
   * @param url
   * @return
   */
  public HttpGet getHttpGet(String url) {
    HttpGet pmethod = new HttpGet(url);
    // 设置响应头信息
    pmethod.addHeader("Connection", "keep-alive");
    pmethod.addHeader("Cache-Control", "max-age=0");
    pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
    return pmethod;
  }

  /**
   * 对微信接口发送https的get请求
   *
   * @param url
   * @return
   */
  public String get(String url) {
    StringBuilder sb = new StringBuilder();
    HttpGet get = new HttpGet(url);
    try {
      HttpResponse resp = httpClient.execute(get);
      try (Scanner in = new Scanner(resp.getEntity().getContent())) {
        while (in.hasNextLine()) {
          sb.append(in.nextLine()).append("\n");
        }
      }
    } catch (IOException e) {
      logger.error("IOException", e);
      throw new RuntimeException(e);
    }
    return sb.toString();
  }

  /**
   * 对微信接口发送https的post请求
   *
   * @param url
   * @param requestBody
   * @return
   */
  public String post(String url, String requestBody) {
    StringBuilder sb = new StringBuilder();
    try {
      HttpPost httpPost = getHttpPost(url);
      httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
      HttpResponse response = httpClient.execute(httpPost);
      try (Scanner scanner = new Scanner(response.getEntity().getContent())) {
        while (scanner.hasNext()) {
          sb.append(scanner.nextLine()).append("\n");
        }
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
    return sb.toString();
  }
}
