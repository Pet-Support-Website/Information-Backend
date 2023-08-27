package seniorproject.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAll();
    Page<Article>findByTitleContainingIgnoreCase(String title, Pageable pageable);
}