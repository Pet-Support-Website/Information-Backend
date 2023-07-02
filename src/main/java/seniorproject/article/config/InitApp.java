package seniorproject.article.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;
import seniorproject.article.repository.ArticleRepository;
import seniorproject.article.repository.TagRepository;

import java.util.ArrayList;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Tag dog, general, food;
        dog = tagRepository.save(Tag.builder().tagname("dog").build());
        general = tagRepository.save(Tag.builder().tagname("general").build());
        food = tagRepository.save(Tag.builder().tagname("food").build());
        ArrayList<Tag> tempTags = new ArrayList<>();
        Article tempArticle = null;
        tempTags.add(dog);
        tempTags.add(general);
        tempArticle = articleRepository.save(Article.builder()
                        .title("General Dog Care")
                        .author("Author Name")
                        .content("A dog can be a wonderful addition to any home, but whether you're an experienced pet parent or a first-time adopter, it's important to keep your canine companion's health and happiness a top priority. Below are some useful tips for all dog parents.")
                        .tags(tempTags)
                        .build());
        dog.getArticles().add(tempArticle);
        general.getArticles().add(tempArticle);
    }
}
