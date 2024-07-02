package ms.Article.repositories;

import ms.Article.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Articlerepository extends JpaRepository<Article, Long> {

}
