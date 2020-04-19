package com.perfume.controller;

import com.perfume.dto.ImageDTO;
import com.perfume.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    UploadFileUtil uploadFileUtil;

    private final String prefix = "tmp/";

    @GetMapping(value = {"/**/{extension:(?:\\w|\\W)+\\.(?:jpg|bmp|gif|jpeg|png|webp)$}"})
    public ResponseEntity<InputStreamResource> getFileImage(@PathVariable String extension, HttpServletRequest request) throws IOException {
        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);


        String fileName = restOfTheUrl.replaceFirst("/api/storage", "");
        Resource imgFile = uploadFileUtil.loadFileAsResource(fileName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> create(@RequestBody ImageDTO image) {
//
//        if (image.getUrl() != null) {
//            String imageUrl = upload(image, prefix + image.getUrl());
//            if (imageUrl.equals("")) {
//                return ResponseEntity.badRequest().build();
//            }
//        }
//        return ResponseEntity.ok(responseMsg);
//    }


}
