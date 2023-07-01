package seniorproject.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import seniorproject.article.entity.Article;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    Long id;
    String tagname;
    ArrayList<TagArticleDTO> articles = new ArrayList<>();
}
