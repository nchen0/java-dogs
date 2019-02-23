package com.lambda.javadogs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SeedDatabase {
    @Bean
    public CommandLineRunner initDB(DogRepository repository) {
        return args -> {
            log.info("Seeding " + repository.save(new Dogs("Springer", 50, false)));
            log.info("Seeding " + repository.save(new Dogs("Bulldog", 45, true)));
            log.info("Seeding " + repository.save(new Dogs("Collie", 50, false)));
            log.info("Seeding " + repository.save(new Dogs("Boston Terrie", 30, true)));
            log.info("Seeding " + repository.save(new Dogs("Corgie", 35, true)));
        };
    }
}
