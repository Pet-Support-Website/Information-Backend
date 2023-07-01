package seniorproject.article.service;

import org.springframework.data.domain.Page;
import seniorproject.article.entity.Article;

public interface ArticleService {
    Integer getArticleSize();
    Page<Article> getArticles(Integer pageSize, Integer page);
    Article getArticle(Long id);
}
