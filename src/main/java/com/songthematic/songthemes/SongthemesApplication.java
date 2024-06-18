package com.songthematic.songthemes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SongthemesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongthemesApplication.class, args);
    }

    @Bean
    public SongSearcher songSearcher() {
        return new SongSearcher();
    }
}
