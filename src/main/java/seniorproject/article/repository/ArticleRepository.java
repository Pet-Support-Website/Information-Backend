package seniorproject.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAll();
    Page<Article>findByTitleContainingIgnoreCase(String title, Pageable pageable);
    @Modifying
    @Query("update Article a set a.title = ?1, a.source = ?2, a.content = ?3 where a.id = ?4")
    @Transactional
    Integer setArticleById(String title, String source, String content, Long id);
}