package com.perfume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadFileConfig {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20848820);
        return multipartResolver;
    }
}
