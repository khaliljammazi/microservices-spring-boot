package ms.stock.services;

import ms.stock.DTO.ArticleDTO;
import ms.stock.DTO.StockDTO;

import java.util.List;
import java.util.Optional;

public interface StockServies {
    public List<StockDTO> getAllStocks();
    public List<StockDTO> getAllStocksWithArticles();
    public Optional<StockDTO> getStockById(String id);
    public StockDTO createStock(StockDTO stock);
    public void deleteStock(String id);
    public ArticleDTO getArticleById(long id);
}
