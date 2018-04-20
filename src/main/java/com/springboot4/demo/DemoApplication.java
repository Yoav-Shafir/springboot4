package com.springboot4.demo;

import com.springboot4.demo.model.Stock;
import com.springboot4.demo.repository.StockMongoReactiveCrudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // This method will be executed after the application context is loaded
    // and right before the Spring Application run method is completed
    @Bean
    CommandLineRunner initData(StockMongoReactiveCrudRepository mongoRepository) {
        return (p) -> {
            mongoRepository.deleteAll().block();
            mongoRepository.save(new Stock("IBM", "IBM Corporation", "Desc")).block();
            mongoRepository.save(new Stock("GGL", "Google", "Desc")).block();
            mongoRepository.save(new Stock("MST", "Microsoft", "Desc")).block();
        };
    }
}
