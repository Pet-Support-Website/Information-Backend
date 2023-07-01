package seniorproject.article.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import seniorproject.article.dto.ArticleDTO;
import seniorproject.article.dto.TagDTO;
import seniorproject.article.entity.Article;
import seniorproject.article.entity.Tag;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ArticleDTO getArticleDto(Article article);

    List<ArticleDTO> getArticlesDto(List<Article> articles);

    TagDTO getTagDTO(Tag tag);

    List<TagDTO> getTagsDTO(List<Tag> tags);
}
