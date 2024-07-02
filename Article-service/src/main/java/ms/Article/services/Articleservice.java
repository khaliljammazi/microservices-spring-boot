package ms.Article.services;

import ms.Article.DTO.ArticleDTO;

import java.util.List;

public interface Articleservice {
    List<ArticleDTO> getAllArticles();
    ArticleDTO getArticleById(Long id);
    ArticleDTO saveArticle(ArticleDTO article , String stock_id);
    ArticleDTO updateArticle(ArticleDTO article);
    void deleteArticle(long id);

}
