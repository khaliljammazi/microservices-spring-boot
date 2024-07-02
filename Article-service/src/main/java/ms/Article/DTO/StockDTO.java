package ms.Article.DTO;

import lombok.Builder;
@Builder

public record StockDTO(String id , String title, String zone) {
}
