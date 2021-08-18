package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/showArticle")
	public String showArticle(Model model) {
		model.addAttribute("articleList", articleRepository.findAll());
		
		return "article/article-list";
	}
}
