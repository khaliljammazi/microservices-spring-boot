package ms.stock.models;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String title;
    private String zone;
}