package seniorproject.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import seniorproject.article.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags();
}
