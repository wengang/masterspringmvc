package io.metaphor.masterSpringMvc;

import io.metaphor.masterSpringMvc.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

import org.springframework.web.filter.ShallowEtagHeaderFilter;


@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSpringMvcApplication.class, args);
    }


}
