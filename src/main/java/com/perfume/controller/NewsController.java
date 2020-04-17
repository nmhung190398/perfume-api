package com.perfume.controller;

import com.perfume.constant.StatusEnum;
import com.perfume.dto.PagingDTO;
import com.perfume.dto.NewsDTO;
import com.perfume.dto.ResponseMsg;
import com.perfume.dto.ResponsePaging;
import com.perfume.dto.mapper.NewsMapper;
import com.perfume.entity.News;
import com.perfume.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @PostMapping("")
    public ResponseEntity<ResponseMsg<News>> create(@RequestBody News body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        body.setId(null);
        newsRepository.save(body);
        return ResponseEntity.ok(new ResponseMsg<>(body,200,""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMsg<News>> update(@RequestBody News body, @PathVariable Long id) {
        body.setStatus(null);
        body.setId(id);
        newsRepository.update(body);
        return ResponseEntity.ok(new ResponseMsg<>(body,200,""));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMsg<Boolean>> delete(@PathVariable Long id) {
        newsRepository.changeStatus(id, StatusEnum.DELETED.getValue());
        return ResponseEntity.ok(new ResponseMsg<>(true,200,""));
    }

    @GetMapping("")
    public ResponseEntity<ResponsePaging<NewsDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<News> pagedResult = newsRepository.getAll(paging);
        List<NewsDTO> newss = new ArrayList<>();

        if (pagedResult.hasContent()) {
            newss = pagedResult.getContent().stream().map(newsMapper::toDTO).collect(Collectors.toList());
        }

        return ResponseEntity.ok(
                new ResponsePaging<>(newss, new PagingDTO(pagedResult.getTotalElements(), page, limit, paging.getOffset()))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (!news.isPresent()) {
            throw new ValidationException("category does not exist");
        }
        return ResponseEntity.ok(newsMapper.toDTO(news.get()));
    }

    // end crud

    @PostMapping("/filter")
    public ResponseEntity<List<NewsDTO>> filter(@RequestBody News body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        List<News> newss = newsRepository.find(body);

        return ResponseEntity.ok(
                newss.stream().map(x -> newsMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/filter/{page}/{limit}")
    public ResponseEntity<ResponsePaging<NewsDTO>> filterPage(@RequestBody News body, @PathVariable int page, @PathVariable int limit) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<News> pagedResult = newsRepository.findPage(body, paging);
        List<NewsDTO> newss = new ArrayList<>();
        if (pagedResult.hasContent()) {
            newss = pagedResult.getContent().stream().map(newsMapper::toDTO).collect(Collectors.toList());
        }
        PagingDTO pagingDTO = new PagingDTO(pagedResult.getTotalElements(), page, limit, paging.getOffset());
        return ResponseEntity.ok(new ResponsePaging<>(newss, pagingDTO));
    }
}
