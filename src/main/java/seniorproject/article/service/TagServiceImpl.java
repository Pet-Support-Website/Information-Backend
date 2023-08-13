package seniorproject.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import seniorproject.article.dao.TagDao;
import seniorproject.article.entity.Tag;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> getTags() {
        return tagDao.getTags(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Tag> searchTags(String tagname, Pageable pageable) {
        return tagDao.searchTags(tagname, pageable);
    }
}
