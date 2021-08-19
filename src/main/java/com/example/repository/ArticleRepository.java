package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事情報を操作するリポジトリ.
 * 
 * @author hayato.saishu
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) ->{
//		
//		Article article = new Article();
//		article.setId(rs.getInt("id"));
//		article.setName(rs.getString("name"));
//		article.setContent(rs.getString("content"));
//		
//		return article;
//	};

	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = new ArrayList<>();
		
		Integer beforeArticleId = 0;
		while (rs.next()) {
			Integer nowArticleId = rs.getInt("a_id");

			if (beforeArticleId != nowArticleId) {
				Article article = new Article();
				article.setId(rs.getInt("a_id"));
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));

				commentList = new ArrayList<>();
				article.setCommentList(commentList);

				articleList.add(article);
			}
			if (rs.getInt("c_id") != 0) {
				Comment comment = new Comment();

				comment.setId(rs.getInt("c_id"));
				comment.setName(rs.getString("c_name"));
				comment.setContent(rs.getString("c_content"));
				comment.setArticleId(rs.getInt("c_article_id"));
				commentList.add(comment);
			}

			beforeArticleId = nowArticleId;
		}
		return articleList;
	};

	/**
	 * 記事情報とコメント情報を全件取得する.
	 * 
	 * @return 記事情報が入ったリスト
	 */
	public List<Article> findAll() {

		String sql = "SELECT a.id AS a_id, a.name AS a_name, a.content AS a_content, c.id AS c_id, c.name AS c_name, c.content AS c_content, c.article_id AS c_article_id FROM articles AS a LEFT OUTER JOIN comments AS c ON a.id=c.article_id ORDER BY a.id DESC, c.id DESC;";

		List<Article> artcleList = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);

		return artcleList;
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param article 記事情報のドメイン
	 */
	public void insert(Article article) {

		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content);";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",
				article.getContent());

		template.update(sql, param);
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事ID
	 */
	public void deleteById(Integer id) {

		String sql = "DELETE FROM articles WHERE id=:id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(sql, param);
	}

}
