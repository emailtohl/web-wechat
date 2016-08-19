package com.github.emailtohl.web.wechat.domain.msg;

import java.util.List;

public class ArticleMsg extends BaseMsg {
	private static final long serialVersionUID = -7813487939360571248L;
	private List<Article> Articles;
	private Integer ArticleCount;

	public ArticleMsg() {
		super("news");
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
