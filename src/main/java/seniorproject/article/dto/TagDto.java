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
public class TagDto {
    Long id;
    String tagname;
    ArrayList<TagArticleDto> articles = new ArrayList<>();
}
