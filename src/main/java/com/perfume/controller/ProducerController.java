package com.perfume.controller;

import com.perfume.constant.StatusEnum;
import com.perfume.dto.CategoryDTO;
import com.perfume.dto.PagingDTO;
import com.perfume.dto.ProducerDTO;
import com.perfume.dto.ResponsePaging;
import com.perfume.dto.mapper.ProducerMapper;
import com.perfume.entity.Category;
import com.perfume.entity.Producer;
import com.perfume.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {
    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProducerMapper producerMapper;

    @PostMapping("")
    public ResponseEntity<Producer> create(@RequestBody Producer body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());

        producerRepository.save(body);
        return ResponseEntity.ok(body);
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Producer body) {
        body.setStatus(null);
        producerRepository.update(body);
        return ResponseEntity.ok("Update Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        producerRepository.changeStatus(id, StatusEnum.DELETED.getValue());
        return ResponseEntity.ok("Delete Success");
    }

    @GetMapping("")
    public ResponseEntity<ResponsePaging<ProducerDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Producer> pagedResult = producerRepository.getAll(paging);
        List<ProducerDTO> producers = new ArrayList<>();

        if (pagedResult.hasContent()) {
            producers = pagedResult.getContent().stream().map(producerMapper::toDTO).collect(Collectors.toList());
        }

        return ResponseEntity.ok(
                new ResponsePaging<>(producers, new PagingDTO(pagedResult.getTotalPages(), page, limit, paging.getOffset()))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerDTO> getById(@PathVariable Long id) {
        Optional<Producer> producer = producerRepository.findById(id);
        if (!producer.isPresent()) {
            throw new ValidationException("category does not exist");
        }
        return ResponseEntity.ok(producerMapper.toDTO(producer.get()));
    }

    // end crud

    @PostMapping("/filter")
    public ResponseEntity<List<ProducerDTO>> filter(@RequestBody Producer body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        List<Producer> producers = producerRepository.find(body);

        return ResponseEntity.ok(
                producers.stream().map(x -> producerMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/filter/{page}/{limit}")
    public ResponseEntity<ResponsePaging<ProducerDTO>> filterPage(@RequestBody Producer body, @PathVariable int page, @PathVariable int limit) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Producer> pagedResult = producerRepository.findPage(body, paging);
        List<ProducerDTO> producers = new ArrayList<>();
        if (pagedResult.hasContent()) {
            producers = pagedResult.getContent().stream().map(producerMapper::toDTO).collect(Collectors.toList());
        }
        PagingDTO pagingDTO = new PagingDTO(pagedResult.getTotalPages(), page, limit, paging.getOffset());
        return ResponseEntity.ok(new ResponsePaging<>(producers, pagingDTO));
    }
}
