package com.songthematic.songthemes.adapter.in.web;

import com.songthematic.songthemes.domain.Song;
import com.songthematic.songthemes.domain.SongSearcher;
import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SongThemesControllerTest {

    @Test
    public void searchReturnsModelWithEmptySearchResults() throws Exception {
        String theme = "new years";
        String songTitle = "aud lang syne";
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withOneSong(new Song(theme, songTitle)));
        Model model = new ConcurrentModel();

        String viewName = songThemesController.themeSearch("christmas", model);
        assertThat(viewName).isEqualTo("theme-search-no-results");
    }

    @Test
    public void searchReturnsModelWithNonEmptySearchResults() throws Exception {
        String theme = "new years";
        String songTitle = "aud lang syne";
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withOneSong(new Song(theme, songTitle)));
        Model model = new ConcurrentModel();

        String viewName = songThemesController.themeSearch("new years", model);

        List<SongView> searchResults = (List<SongView>) model.getAttribute("searchResults");
        assertThat(viewName).isEqualTo("theme-search-has-results");
        assertThat(searchResults).containsExactly(new SongView("aud lang syne"));
    }

    @Test
    public void searchReturnsModelWithMultipleSearchResults() throws Exception {
        String theme = "new years";
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withMultipleSongs(
                        new Song(theme, "aud lang syne"),
                        new Song(theme, "New Year's Eve is a Haunted House")
                ));
        Model model = new ConcurrentModel();

        songThemesController.themeSearch("new years", model);

        List<SongView> searchResults = (List<SongView>) model.getAttribute("searchResults");
        assertThat(searchResults).containsExactly(
                new SongView("aud lang syne"),
                new SongView("New Year's Eve is a Haunted House"));
    }
}
