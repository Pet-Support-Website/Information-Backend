package seniorproject.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import seniorproject.article.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Page<Tag>findByTagname(String tagname, Pageable pageable);
}