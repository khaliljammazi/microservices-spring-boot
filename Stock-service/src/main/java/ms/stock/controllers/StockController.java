package ms.stock.controllers;
import ms.stock.DTO.ArticleDTO;
import ms.stock.DTO.StockDTO;
import ms.stock.services.Imp.StockServiceImp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.stock.services.StockServies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/stock")
public class StockController {

     StockServies stockServiceImp;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockServiceImp.getAllStocks());
    }

    @GetMapping("/test/{id}")
     public ResponseEntity<ArticleDTO> getArticleById(@PathVariable long id) {
        return ResponseEntity.ok(stockServiceImp.getArticleById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getSStockById(@PathVariable String id) {
        return stockServiceImp.getStockById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<StockDTO> createArticle(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockServiceImp.createStock(stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        stockServiceImp.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
