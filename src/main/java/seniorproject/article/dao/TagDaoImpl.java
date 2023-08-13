package seniorproject.article.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import seniorproject.article.entity.Tag;
import seniorproject.article.repository.TagRepository;

@Repository
public class TagDaoImpl implements TagDao{
    @Autowired
    TagRepository tagRepository;

    @Override
    public Page<Tag> getTags(Pageable pageRequest) {
        return tagRepository.findAll(pageRequest);
    }

    @Override
    public Page<Tag> searchTags(String tagname, Pageable pageable) {
        return tagRepository.findByTagname(tagname, pageable);
    }
}
