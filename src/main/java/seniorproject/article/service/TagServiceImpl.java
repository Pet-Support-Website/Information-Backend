package seniorproject.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import seniorproject.article.dao.TagDao;
import seniorproject.article.entity.Tag;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagDao tagDao;

    @Override
    public Page<Tag> getTags(Pageable pageRequest) {
        return tagDao.getTags(pageRequest);
    }
}
