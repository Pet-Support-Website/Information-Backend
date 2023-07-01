package seniorproject.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import seniorproject.article.entity.Tag;

public interface TagService {
    Page<Tag> getTags(Pageable pageRequest);
}
