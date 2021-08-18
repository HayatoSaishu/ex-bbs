package com.example.form;

/**
 * 記事を投稿する際のリクエストパラメータを受け取るフォーム.
 * 
 * @author hayato.saishu
 *
 */
public class ArticleForm {

	/**　投稿者名　*/
	private String name;
	/**　記事内容　*/
	private String content;

	public ArticleForm() {
	}

	public ArticleForm(String name, String content) {
		super();
		this.name = name;
		this.content = content;
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
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

}
