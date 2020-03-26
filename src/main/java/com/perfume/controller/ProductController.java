package com.perfume.controller;

import com.perfume.constant.StatusEnum;
import com.perfume.dto.mapper.ProductMapper;
import com.perfume.entity.Product;
import com.perfume.repository.ProductRepository;
import com.perfume.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UploadFileUtil uploadFileUtil;

    @PostMapping("/add")
    public ResponseEntity<Product> create(@RequestBody Product body) {
        if (productRepository.existsByCode(body.getCode())) {
            throw new ValidationException("code already existed");
        }
        body.setStatus(StatusEnum.ACTIVE.getValue());

        productRepository.save(body);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/uploadBase64")
    public String upload(@RequestBody Map<String, Object> image) {
        String base64Image = image.get("base64").toString();
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }

        uploadFileUtil.saveFile(base64Image, image.get("fileName").toString());
        return "ok";
    }

    @GetMapping("/imageBase64/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String fileName) throws IOException {
        String filePath = uploadFileUtil.getFilePath(uploadFileUtil.uploadFolder, fileName);
        Resource imgFile = uploadFileUtil.loadFileAsResource(filePath);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
