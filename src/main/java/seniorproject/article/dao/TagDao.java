package seniorproject.article.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import seniorproject.article.entity.Tag;

public interface TagDao {
    Page<Tag> getTags(Pageable pageRequest);
}
