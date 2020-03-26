package com.perfume.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class UploadFileUtil {
    @Value("${file.upload-dir}")
    public String uploadFolder;

    public boolean saveFile(String base64Image, String fileName) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
        String filePath = getFilePath(uploadFolder, fileName);
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), decodedBytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getFilePath(String uploadFolder, String fileName) {
        return Paths.get(uploadFolder, fileName).toAbsolutePath().normalize().toString();
    }

    public Resource loadFileAsResource(String fileName) {
        Path fileStorageLocation = Paths.get(uploadFolder)
                .toAbsolutePath().normalize();
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                System.out.println("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            System.out.println("File not found " + fileName);
        }
        return null;
    }
}
