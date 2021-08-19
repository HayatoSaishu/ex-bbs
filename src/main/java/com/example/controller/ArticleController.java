package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事情報を操作するコントローラ.
 * 
 * @author hayato.saishu
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 記事情報一覧とコメント情報一覧を表示する.
	 * 
	 * @param model リクエストスコープへ格納
	 * @return 記事情報一覧のページへフォワード
	 */
	@RequestMapping("")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();

//		for (Article article : articleList) {
//			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
//			article.setCommentList(commentList);
//		}

		model.addAttribute("articleList", articleList);
		return "article/article-list";
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param form リクエストパラメータを取得
	 * @return 記事一覧ページへリダイレクト
	 */
	@RequestMapping("/insert-article")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);

		articleRepository.insert(article);

		return "redirect:/article";
	}

	/**
	 * フォームで受け取った内容をcommentsテーブルにインサートする.
	 * 
	 * @param form コメントを投稿する際のリクエストパラメータを受け取るフォーム
	 * @return トップページへリダイレクト
	 */
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);

		commentRepository.insert(comment);

		return "redirect:/article";
	}

	/**
	 * 記事とコメントの削除.
	 * 
	 * @param id        記事ID
	 * @param articleId 記事ID
	 * @return トップページを表示
	 */
	@RequestMapping("/delete-article")
	public String deleteArticle(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);

		return "redirect:/article";
	}

}
