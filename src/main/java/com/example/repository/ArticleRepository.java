package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

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

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) ->{
		
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	/**
	 * 記事情報を全件取得する.
	 * 
	 * @return 記事情報が入ったリスト
	 */
	public List<Article> findAll(){
		
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		
		List<Article> artcleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return artcleList;
	}
	
	/**
	 * 記事を投稿する/
	 * 
	 * @param article 記事情報のドメイン
	 */
	public void insert(Article article) {
		
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content);";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		
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
