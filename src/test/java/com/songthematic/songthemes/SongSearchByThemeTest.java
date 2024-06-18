package com.songthematic.songthemes;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
public class SongSearchByThemeTest {
    @Test
    public void searchForThemeThatDoesNotExistReturnsNoResults() throws Exception {
        SongSearcher songSearcher = new SongSearcher();

        List<String> foundSongs = songSearcher.byTheme("Applesauce");

        assertThat(foundSongs)
                .isEmpty();
    }
}
