package ms.Article.controllers;

import lombok.AllArgsConstructor;
import ms.Article.DTO.ArticleDTO;
import ms.Article.services.Articleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final Articleservice articleService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable long id) {
        ArticleDTO article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/add/{stock_id}")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO , @PathVariable String stock_id) {
        ArticleDTO savedArticle = articleService.saveArticle(articleDTO , stock_id);
        return ResponseEntity.status(201).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable long id, @RequestBody ArticleDTO articleDTO) {
        ArticleDTO updatedArticle = articleService.updateArticle(articleDTO.toBuilder().id(id).build());
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
