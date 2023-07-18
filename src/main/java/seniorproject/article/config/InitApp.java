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

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    ArrayList<String> tagsNameAlreadyInDB = new ArrayList<>();
    ArrayList<Tag> tagsAlreadyInDB = new ArrayList<>();
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagRepository tagRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ClassPathResource DataFile = new ClassPathResource("pet data table V.1.0.csv");
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
        if (Arrays.stream(v).toList().size() >= 4){
            String title = Arrays.stream(v).toList().get(0);
            String source = Arrays.stream(v).toList().get(1);
            String content = Arrays.stream(v).toList().get(2);
            String tags = Arrays.stream(v).toList().get(3);
            String imageurls = null;
            if (Arrays.stream(v).toList().size() > 4){
                imageurls = Arrays.stream(v).toList().get(4);
            }
            List<String> splitedTag = Arrays.stream(tags.split(", ")).toList();
            ArrayList<Tag> temptags = new ArrayList<>();
            splitedTag.forEach(t -> {
                if (!tagsNameAlreadyInDB.contains(t)){
                    tagsNameAlreadyInDB.add(t);
                    Tag tempTag = tagRepository.save(Tag.builder().tagname(t).build());
                    tagsAlreadyInDB.add(tempTag);
                    temptags.add(tempTag);
                } else {
                    int index = tagsNameAlreadyInDB.indexOf(t);
                    Tag tempTag = tagsAlreadyInDB.get(index);
                    temptags.add(tempTag);
                }
            });
            Article tempArticle = articleRepository.save(Article.builder()
                    .title(title)
                    .source(source)
                    .content(content)
                    .tags(temptags)
                    .imgUrl(imageurls)
                    .build());
            temptags.forEach(tt -> tt.getArticles().add(tempArticle));
        }
    }
}
