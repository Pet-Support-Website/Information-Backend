package seniorproject.article.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String tagname;
    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    List<Article> articles = new ArrayList<>();
}