package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

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
	
	/**
	 * 記事情報一覧を表示する.
	 * 
	 * @param model リクエストスコープへ格納
	 * @return　記事情報一覧」のページへフォワード
	 */
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("articleList", articleRepository.findAll());
		
		return "article/article-list";
	}
	
	/**
	 * 記事を投稿する.
	 * 
	 * @param form リクエストパラメータを取得
	 * @return　記事一覧ページへリダイレクト
	 */
	@RequestMapping("/insert-article")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		
		articleRepository.insert(article);
		
		return "redirect:/article";
	}
}
