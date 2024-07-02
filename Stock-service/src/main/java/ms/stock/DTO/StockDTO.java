package ms.stock.DTO;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;


@Builder
public record StockDTO(String id , String title, String zone, List<ArticleDTO> articles) implements Serializable {

}