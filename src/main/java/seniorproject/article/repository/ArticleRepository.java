package seniorproject.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import seniorproject.article.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,String> {
    List<Article> findAll();
}