package ms.stock.repositories;
import ms.stock.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StockRepository extends MongoRepository<Stock,String> {
}

