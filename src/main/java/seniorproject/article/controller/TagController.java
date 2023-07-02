package seniorproject.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorproject.article.service.TagService;
import seniorproject.article.util.ProjectMapper;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    ResponseEntity<?> getTags() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getTagsDTO(tagService.getTags()));
    }
}
