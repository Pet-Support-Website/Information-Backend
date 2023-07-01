package seniorproject.article.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

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
    @ManyToMany
    ArrayList<Article> articles = new ArrayList<>();
}