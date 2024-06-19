package com.songthematic.songthemes;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
public class SongSearchByThemeTest {
    @Test
    public void searchForThemeThatDoesNotExistReturnsNoResults() throws Exception {
        SongSearcher songSearcher = SongSearcher.withNoSongs("new years");

        List<String> foundSongs = songSearcher.byTheme("Applesauce");

        assertThat(foundSongs)
                .isEmpty();
    }

    @Test
    public void searchForThemeFindsOneMatchingSong() throws Exception {
        SongSearcher songSearcher = SongSearcher.withOneSong(
                new Song("new years", "auld lang syne"));

        List<String> foundSongs = songSearcher.byTheme("New Years");

        assertThat(foundSongs)
                .containsExactly("auld lang syne");
    }
}
