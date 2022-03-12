package helei.wx.oa.message;

import helei.wx.oa.OAConst;

import java.util.List;

public class ArticleResponse extends OAResponse {
  private List<Article> Articles;
  private Integer ArticleCount;

  public ArticleResponse(OARequest request) {
    super(OAConst.NEWS, request);
  }

  public List<Article> getArticles() {
    return Articles;
  }

  public void setArticles(List<Article> articles) {
    this.Articles = articles;
    this.ArticleCount = articles.size();
  }

  public Integer getArticleCount() {
    return ArticleCount;
  }

  public void setArticleCount(Integer articleCount) {
    this.ArticleCount = articleCount;
  }

  @Override
  public String toString() {
    return "ArticleMsg [Articles=" + Articles + ", ArticleCount=" + ArticleCount + ", ToUserName=" + ToUserName
        + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
  }
}
