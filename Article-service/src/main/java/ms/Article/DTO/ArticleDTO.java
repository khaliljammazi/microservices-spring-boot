package ms.Article.DTO;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ms.Article.models.Article}
 */

@Builder
public record ArticleDTO(Long id ,String name, float quantity, String stock_id,StockDTO stock) implements Serializable {
    public ArticleDTOBuilder toBuilder() {
        return ArticleDTO.builder()
                .id(this.id)
                .name(this.name)
                .quantity(this.quantity)
                .stock_id(this.stock_id)
                .stock(this.stock);
    }
}