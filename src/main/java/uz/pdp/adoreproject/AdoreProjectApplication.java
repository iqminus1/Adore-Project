package uz.pdp.adoreproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Random;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class AdoreProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoreProjectApplication.class, args);
    }


    @Bean
    public Random random(){
        return new Random();
    }


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("user");
    }
}
