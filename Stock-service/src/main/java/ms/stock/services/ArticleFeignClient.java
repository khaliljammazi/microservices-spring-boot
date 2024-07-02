package ms.stock.services;

import ms.stock.DTO.ArticleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "article-service", url = "http://localhost:8097" , path = "/articles")
public interface ArticleFeignClient {

    @GetMapping("/")
    List<ArticleDTO> getAllArticles();
    @GetMapping("/{id}")
    ArticleDTO getArticleById(@PathVariable long id);
}

