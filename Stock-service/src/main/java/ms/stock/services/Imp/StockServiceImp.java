package ms.stock.services.Imp;

import ms.stock.DTO.ArticleDTO;
import ms.stock.DTO.StockDTO;
import ms.stock.models.Stock;
import ms.stock.repositories.StockRepository;
import ms.stock.services.ArticleFeignClient;
import ms.stock.services.StockServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImp implements StockServies {

    @Autowired
     StockRepository stockRepository;

      ArticleFeignClient articleFeignClient;


    public List<StockDTO> getAllStocks() {

        return stockRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> getAllStocksWithArticles() {
       return stockRepository.findAll().stream()
             .map(stock -> {
                  StockDTO stockDTO = convertToDTO(stock);

                    List<ArticleDTO> articles = articleFeignClient.getAllArticles().stream()
                            .filter(article -> article.stock_id().equals(stockDTO.id()))
                          .collect(Collectors.toList());
                    return new StockDTO(stockDTO.id(), stockDTO.title(), stockDTO.zone(), articles);
              })
              .collect(Collectors.toList());


    }




    public Optional<StockDTO> getStockById(String id) {
        return stockRepository.findById(id)
                .map(this::convertToDTO);
    }

    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = Stock.builder()
                .title(stockDTO.title())
                .zone(stockDTO.zone())
                .build();
        Stock savedStock = stockRepository.save(stock);
        return convertToDTO(savedStock);
    }

    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }

    @Override
    public ArticleDTO getArticleById(long id) {
        return null;
    }

    private StockDTO convertToDTO(Stock stock) {
        return StockDTO.builder()
                .id(stock.getId())
                .title(stock.getTitle())
                .zone(stock.getZone())
                .articles(List.of())
                .build();
    }
}
