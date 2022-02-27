package ru.alov.springboot;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import ru.alov.springboot.entities.Category;
import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.Product;

@SpringBootApplication
public class SpringbootApplication {

    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }


}
