package com.example.form;

/**
 * コメント投稿の際のリクエストパラメータを受け取るフォーム.
 * 
 * @author hayato.saishu
 *
 */
public class CommentForm {

	/** 記事ID */
	private Integer articleId;
	/** コメント者名 */
	private String name;
	/** コメント内容 */
	private String content;

	public CommentForm() {
	}

	public CommentForm(Integer articleId, String name, String content) {
		super();
		this.articleId = articleId;
		this.name = name;
		this.content = content;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
