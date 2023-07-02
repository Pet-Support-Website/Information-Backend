package seniorproject.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorproject.article.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}