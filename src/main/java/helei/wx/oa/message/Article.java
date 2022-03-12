package helei.wx.oa.message;

public class Article {
  /**
   * 标题
   */
  private String Title;
  /**
   * 描述
   */
  private String Description;
  /**
   * 点击后跳转的链接
   */
  private String Url;
  /**
   * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
   */
  private String PicUrl;

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    this.Title = title;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    this.Description = description;
  }

  public String getUrl() {
    return Url;
  }

  public void setUrl(String url) {
    this.Url = url;
  }

  public String getPicUrl() {
    return PicUrl;
  }

  public void setPicUrl(String picurl) {
    this.PicUrl = picurl;
  }

  @Override
  public String toString() {
    return "Article [Title=" + Title + ", Description=" + Description + ", Url=" + Url + ", PicUrl=" + PicUrl + "]";
  }
}