package com.github.emailtohl.web.wechat.domain.auth;

import java.io.Serializable;

/**
 * 存储访问token的对象
 *
 * @author HeLei
 */
public class AccessToken implements Serializable {
  protected String access_token;
  protected Integer expires_in;
  protected Integer errcode;
  protected String errmsg;

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public Integer getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(Integer expires_in) {
    this.expires_in = expires_in;
  }

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  @Override
  public String toString() {
    return "JsonBean [access_token=" + access_token + ", expires_in=" + expires_in + ", errcode=" + errcode
        + ", errmsg=" + errmsg + "]";
  }
}
