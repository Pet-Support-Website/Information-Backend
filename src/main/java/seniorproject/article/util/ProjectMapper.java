package seniorproject.article.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import seniorproject.article.dto.ArticleDto;
import seniorproject.article.dto.TagDto;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ArticleDto getArticleDto(Article article);

    List<ArticleDto> getArticlesDto(List<Article> articles);

    TagDto getTagDto(Tag tag);

    List<TagDto> getTagsDto(List<Tag> tags);
}
