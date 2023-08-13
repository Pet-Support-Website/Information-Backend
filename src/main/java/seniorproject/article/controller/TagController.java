package seniorproject.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seniorproject.article.entity.Tag;
import seniorproject.article.service.TagService;
import seniorproject.article.util.ProjectMapper;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    ResponseEntity<?> getTags() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getTagsDto(tagService.getTags()));
    }

    @GetMapping("/searchTags")
    ResponseEntity<?> searchTags(@RequestParam(value = "_tagname", required = false) String tagname) {
        Integer perPage = 100;
        Integer page = 0;
        Page<Tag> pageoutput;
        pageoutput = tagService.searchTags(tagname, PageRequest.of(page, perPage));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageoutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getTagsDto(pageoutput.getContent()), responseHeader, HttpStatus.OK);
    }
}
