package org.example.xmlloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Controller", "Repository", "Model"})
@EntityScan(basePackages = "Model")
@EnableJpaRepositories(basePackages = "Repository")
public class XmlloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlloaderApplication.class, args);
    }

}