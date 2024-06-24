package com.songthematic.songthemes.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({
            "new years,New Years",
            "new years,new years",
            "New Years,New Years",
            "New Years,new years",
    })
    public void searchForThemeFindsOneMatchingSong(String songTheme, String requestedTheme) throws Exception {
        SongSearcher songSearcher = SongSearcher.withOneSong(
                new Song(songTheme, "auld lang syne"));

        List<String> foundSongs = songSearcher.byTheme(requestedTheme);

        assertThat(foundSongs)
                .containsExactly("auld lang syne");
    }

    @Test
    public void searchForThemeFindsMultipleMatchingSongs() throws Exception {
        SongSearcher songSearcher = SongSearcher.withMultipleSongs(
                new Song("new years", "auld lang syne"),
                new Song("new years", "New Year's Eve In a Haunted House")
        );

        List<String> foundSongs = songSearcher.byTheme("New Years");

        assertThat(foundSongs)
                .containsExactly(
                        "auld lang syne",
                        "New Year's Eve In a Haunted House");
    }

    @Test
    public void forSongsWithMultipleThemesSearchFindsAllSongs() throws Exception {
        SongSearcher songSearcher = SongSearcher.withMultipleSongs(
                new Song("new years", "auld lang syne"),
                new Song("christmas", "The Christmas Tree is on Fire")
        );

        assertThat(songSearcher.byTheme("new years"))
                .containsExactly("auld lang syne");
        assertThat(songSearcher.byTheme("Christmas"))
                .containsExactly("The Christmas Tree is on Fire");
    }
}
