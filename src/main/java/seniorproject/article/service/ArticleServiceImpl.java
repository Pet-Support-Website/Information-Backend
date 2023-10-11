package seniorproject.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import seniorproject.article.dao.ArticleDao;
import seniorproject.article.entity.Article;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleDao articleDao;
    @Override
    public Integer getArticleSize() {
        return articleDao.getArticleSize();
    }

    @Override
    public Page<Article> getArticles(Integer pageSize, Integer page) {
        return articleDao.getArticles(pageSize, page);
    }

    @Override
    public Article getArticle(Long id) {
        return articleDao.getArticle(id);
    }

    @Override
    public Page<Article> searchTitle(String title, Pageable pageable) {
        return articleDao.searchTitle(title, pageable);
    }

    @Override
    public Article save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public boolean deleteById(Long id) {
        return articleDao.deleteById(id);
    }

    @Override
    public Integer updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }
}
