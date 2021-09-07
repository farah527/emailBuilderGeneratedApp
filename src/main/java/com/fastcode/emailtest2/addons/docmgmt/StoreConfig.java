 package com.fastcode.emailtest2.addons.docmgmt;
 
import java.io.IOException;

import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
 @Configuration
 @EnableJpaRepositories(basePackages = {"com.fastcode.emailtest2.addons.docmgmt", "org.springframework.versions"})
 @EnableFilesystemStores(basePackages = {"org.springframework.content.elasticsearch"})
 @Import(org.springframework.content.rest.config.RestConfiguration.class)
 public class StoreConfig {

     @Bean
     FileSystemResourceLoader fileSystemResourceLoader() throws IOException {
         return new FileSystemResourceLoader(new java.io.File("C:\\tmp\\uploadedFiles").getAbsolutePath());
     }
 }

