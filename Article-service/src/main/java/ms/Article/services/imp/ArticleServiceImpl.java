package ms.Article.services.imp;

import lombok.extern.slf4j.Slf4j;
import ms.Article.DTO.ArticleDTO;
import ms.Article.DTO.StockDTO;
import ms.Article.models.Article;
import ms.Article.repositories.Articlerepository;
import ms.Article.services.Articleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl implements Articleservice {

  private final Articlerepository articleRepository;
  private final RestTemplate restTemplate;

  @Value("${stock.service.url}")
  private String stockServiceUrl;

  @Autowired
  public ArticleServiceImpl(Articlerepository articleRepository, RestTemplate restTemplate) {
    this.articleRepository = articleRepository;
    this.restTemplate = restTemplate;
  }

  private ArticleDTO mapToDTO(Article article, StockDTO stockDTO) {
    return ArticleDTO.builder()
            .id(article.getId())
            .name(article.getName())
            .quantity(article.getQuantity())
            .stock_id(article.getStock_id())
            .stock(stockDTO)
            .build();
  }

  private Article mapToEntity(ArticleDTO articleDTO) {
    return Article.builder()
            .id(articleDTO.id())
            .name(articleDTO.name())
            .quantity(articleDTO.quantity())
            .stock_id(articleDTO.stock_id())
            .build();
  }

  @Override
  public ArticleDTO saveArticle(ArticleDTO articleDTO, String stock_id) {
    // Fetch the StockDTO from the stock service using the provided stock_id
    StockDTO stockDTO = fetchStockById(stock_id);

    // Map the ArticleDTO to an Article entity
    Article article = mapToEntity(articleDTO);

    article.setStock_id(stock_id);


    Article savedArticle = articleRepository.save(article);

    return mapToDTO(savedArticle, stockDTO);
  }

  @Override
  public List<ArticleDTO> getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    return articles.stream()
            .map(article -> {
              StockDTO stockDTO = fetchStockById(article.getStock_id());
              return mapToDTO(article, stockDTO);
            })
            .collect(Collectors.toList());
  }

  @Override
  public ArticleDTO getArticleById(Long id) {
    Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));
    StockDTO stockDTO = fetchStockById(article.getStock_id());
    return mapToDTO(article, stockDTO);
  }



  @Override
  public ArticleDTO updateArticle(ArticleDTO articleDTO) {
    Article article = articleRepository.findById(articleDTO.id())
            .orElseThrow(() -> new RuntimeException("Article not found"));
    article.setName(articleDTO.name());
    article.setQuantity(articleDTO.quantity());
    article.setStock_id(articleDTO.stock_id());
    Article updatedArticle = articleRepository.save(article);
    StockDTO stockDTO = fetchStockById(updatedArticle.getStock_id());
    return mapToDTO(updatedArticle, stockDTO);
  }

  @Override
  public void deleteArticle(long id) {
    Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));
    articleRepository.delete(article);
  }

  private StockDTO fetchStockById(String stock_id) {
    try {
      return restTemplate.getForObject(stockServiceUrl + "/stock/" + stock_id, StockDTO.class);
    } catch (HttpClientErrorException.NotFound e) {
      log.error("Stock not found for id: {}", stock_id);
      throw new RuntimeException("Stock not found for id: " + stock_id);
    }
  }
}
