package seniorproject.article.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String title;
    String author;
    String content;
    @ManyToMany(mappedBy = "articles")
    ArrayList<Tag> tags;
}