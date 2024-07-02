package ms.stock.DTO;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ArticleDTO(Long id ,String name, float quantity, String stock_id) implements Serializable {

}