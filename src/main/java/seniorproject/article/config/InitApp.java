package seniorproject.article.config;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;
import seniorproject.article.repository.ArticleRepository;
import seniorproject.article.repository.TagRepository;
import seniorproject.article.service.CsvDataService;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;

    @Autowired
    CsvDataService csvDataService;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        Tag dog, general, food;
//        dog = tagRepository.save(Tag.builder().tagname("dog").build());
//        general = tagRepository.save(Tag.builder().tagname("general").build());
//        food = tagRepository.save(Tag.builder().tagname("food").build());
//        ArrayList<Tag> tempTags = new ArrayList<>();
//        Article tempArticle = null;
//        tempTags.add(dog);
//        tempTags.add(general);
//        tempArticle = articleRepository.save(Article.builder()
//                        .title("General Dog Care")
//                        .source("Author Name")
//                        .content("A dog can be a wonderful addition to any home, but whether you're an experienced pet parent or a first-time adopter, it's important to keep your canine companion's health and happiness a top priority. Below are some useful tips for all dog parents.")
//                        .tags(tempTags)
//                        .build());
//        dog.getArticles().add(tempArticle);
//        general.getArticles().add(tempArticle);
//        tempTags = new ArrayList<>();
//        tempTags.add(dog);
//        tempTags.add(food);
//        tempArticle = articleRepository.save(Article.builder()
//                .title("Dog's Food")
//                .source("Author Name")
//                .content("• Puppies eight to 12 weeks old need four meals a day./n• Feed puppies three to six months old three meals a day./n• Feed puppies six months to one year two meals a day./n• When your dog reaches his first birthday, one meal a day is usually enough./n• For some dogs, including larger canines or those prone to bloat, it's better to feed two smaller meals./nPremium-quality dry food provides a well-balanced diet for adult dogs and may be mixed with water, broth or canned food. Your dog may enjoy cottage cheese, cooked egg or fruits and vegetables, but these additions should not total more than ten percent of his daily food intake./nPuppies should be fed a high-quality, brand-name puppy food (large breed puppy foods for large breeds). Please limit 'people food' however, because it can result in vitamin and mineral imbalances, bone and teeth problems and may cause very picky eating habits and obesity. Clean, fresh water should be available at all times, and be sure to wash food and water dishes frequently.")
//                .tags(tempTags)
//                .build());
//        dog.getArticles().add(tempArticle);
//        food.getArticles().add(tempArticle);
        ClassPathResource DataFile = new ClassPathResource("pet data table V.0.1.csv");
        try {
            FileReader fileReader = new FileReader(DataFile.getFile());
            CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(fileReader);
            List<String[]> articlesData = csvReader.readAll().stream().toList();
            articlesData.forEach(this::saveToDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveToDB(String[] v) {
        if (Arrays.stream(v).toList().size() == 4){
            String title = Arrays.stream(v).toList().get(0);
            String source = Arrays.stream(v).toList().get(1);
            String content = Arrays.stream(v).toList().get(2);
            String tags = Arrays.stream(v).toList().get(3);
            List<String> splitedTag = Arrays.stream(tags.split(", ")).toList();
            ArrayList<Tag> temptags = new ArrayList<>();
            splitedTag.forEach(t -> {
                Tag tempTag = tagRepository.save(Tag.builder().tagname(t).build());
                temptags.add(tempTag);
            });
            Article tempArticle = articleRepository.save(Article.builder()
                    .title(title)
                    .source(source)
                    .content(content)
                    .tags(temptags)
                    .build());
            temptags.forEach(tt -> tt.getArticles().add(tempArticle));
        }
    }
}
