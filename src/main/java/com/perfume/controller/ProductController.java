package com.perfume.controller;

import com.perfume.constant.StatusEnum;
import com.perfume.dto.PagingDTO;
import com.perfume.dto.ProductDTO;
import com.perfume.dto.ResponsePaging;
import com.perfume.dto.mapper.ProductMapper;
import com.perfume.entity.Product;
import com.perfume.repository.ProductRepository;
import com.perfume.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UploadFileUtil uploadFileUtil;

    private final String imgHash = "/api/product/image/";

    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody Product body) {
        if (body.code == null || body.code.equals("")) {
            throw new ValidationException("code required");
        }
        if (productRepository.existsByCode(body.getCode())) {
            throw new ValidationException("code already existed");
        }
        String imageUrl = upload(body.image, body.code);
        if (imageUrl.equals("")) {
            throw new ValidationException("invalid image type for base64");
        }
        body.setImage(imgHash+imageUrl);
        body.setStatus(StatusEnum.ACTIVE.getValue());

        productRepository.save(body);
        return ResponseEntity.ok(body);
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Product body) {
        body.setStatus(null);
        productRepository.update(body);
        return ResponseEntity.ok("Update Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productRepository.changeStatus(id, StatusEnum.DELETED.getValue());
        return ResponseEntity.ok("Delete Success");
    }

    @GetMapping("")
    public ResponseEntity<ResponsePaging<ProductDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Product> pagedResult = productRepository.getAll(paging);
        List<ProductDTO> products = new ArrayList<>();

        if (pagedResult.hasContent()) {
            products = pagedResult.getContent().stream().map(productMapper::toDTO).collect(Collectors.toList());
        }

        return ResponseEntity.ok(
                new ResponsePaging<>(products, new PagingDTO(pagedResult.getTotalPages(), page, limit, paging.getOffset()))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ValidationException("category does not exist");
        }
        return ResponseEntity.ok(productMapper.toDTO(product.get()));
    }

    // end crud

    @PostMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filter(@RequestBody Product body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        List<Product> products = productRepository.find(body);

        return ResponseEntity.ok(
                products.stream().map(x -> productMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/filter/{page}/{limit}")
    public ResponseEntity<ResponsePaging<ProductDTO>> filterPage(@RequestBody Product body, @PathVariable int page, @PathVariable int limit) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<Product> pagedResult = productRepository.findPage(body, paging);
        List<ProductDTO> products = new ArrayList<>();
        if (pagedResult.hasContent()) {
            products = pagedResult.getContent().stream().map(productMapper::toDTO).collect(Collectors.toList());
        }
        PagingDTO pagingDTO = new PagingDTO(pagedResult.getTotalPages(), page, limit, paging.getOffset());
        return ResponseEntity.ok(new ResponsePaging<>(products, pagingDTO));
    }

    public String upload(String image, String fileName) {
        String base64Image = image;
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }

        return uploadFileUtil.saveFile(base64Image, fileName);
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String fileName) throws IOException {
        Resource imgFile = uploadFileUtil.loadFileAsResource(fileName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
