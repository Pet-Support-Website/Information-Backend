package seniorproject.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    Long id;
    String title;
    String source;
    String content;
    ArrayList<ArticleTagDto> tags;
    String imgUrl;
}
