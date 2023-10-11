package seniorproject.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import seniorproject.article.dto.ArticleDto;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;
import seniorproject.article.service.ArticleService;
import seniorproject.article.util.ProjectMapper;

@Controller
public class ArticlesController {
    @Autowired
    ArticleService articleService;

    @GetMapping("article")
    public ResponseEntity<?> getArticleList(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page
    ) {
        perPage = perPage == null?24:perPage;
        page = page == null?1:page;
        Page<Article> pageOutput;
        pageOutput = articleService.getArticles(perPage,page);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getArticlesDto(pageOutput.getContent()),responseHeader, HttpStatus.OK);
    }

    @GetMapping("article/{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") Long id) {
        Article output = articleService.getArticle(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getArticleDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @GetMapping("/searchTitle")
    ResponseEntity<?> searchTitle(@RequestParam(value = "_title", required = false) String title) {
        Integer perPage = 100;
        Integer page = 0;
        Page<Article> pageoutput;
        pageoutput = articleService.searchTitle(title, PageRequest.of(page, perPage));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageoutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getArticlesDto(pageoutput.getContent()), responseHeader, HttpStatus.OK);
    }
    @PostMapping("/addArticle")
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
        Article output = articleService.save(article);
        System.out.println(output);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getArticleDto(output));
    }

    @PostMapping("/deleteArticle")
    public ResponseEntity<?> deleteArticle(@RequestBody Article article) {
        ResponseEntity<ArticleDto> output = null;
        if (articleService.deleteById(article.getId())) {
            output = ResponseEntity.ok(ProjectMapper.INSTANCE.getArticleDto(article));
            System.out.println(output);
        }
        return output;
    }
}
