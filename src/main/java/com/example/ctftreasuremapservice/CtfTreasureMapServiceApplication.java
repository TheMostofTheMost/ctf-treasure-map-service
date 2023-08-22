package com.example.ctftreasuremapservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication()
public class CtfTreasureMapServiceApplication {

    public static void main(String[] args) {
//		https://www.dreamstime.com/hud-map-france-regions-cyberpunk-futuristic-digital-dark-blue-background-editable-stroke-vector-hud-map-france-image184244541
        // hud map гугли
        SpringApplication.run(CtfTreasureMapServiceApplication.class, args);
    }

}
