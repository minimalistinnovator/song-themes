package com.songthematic.songthemes;

import com.songthematic.songthemes.domain.Song;
import com.songthematic.songthemes.domain.SongSearcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SongThemesStartup {

    public static void main(String[] args) {
        SpringApplication.run(SongThemesStartup.class, args);
    }

    @Bean
    public SongSearcher songSearcher() {
        return SongSearcher.withMultipleSongs(
                new Song("new years", "auld lang syne"),
                new Song("new years", "New Year's Even In A Haunted House"),
                new Song("christmas", "The Christmas Tree is On Fire")
        );
    }
}
