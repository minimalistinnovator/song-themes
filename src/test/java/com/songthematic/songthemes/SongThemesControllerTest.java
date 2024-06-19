package com.songthematic.songthemes;

import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SongThemesControllerTest {

    @Test
    public void searchReturnsSearchResultsView() throws Exception {
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withNoSongs("new years"));
        Model model = new ConcurrentModel();

        String viewName = songThemesController.themeSearch("christmas", model);

        assertThat(viewName).isEqualTo("theme-search-results");
    }

    @Test
    public void searchReturnsModelWithEmptySearchResults() throws Exception {
        String theme = "new years";
        String songTitle = "aud lang syne";
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withOneSong(new Song(theme, songTitle)));
        Model model = new ConcurrentModel();

        songThemesController.themeSearch("christmas", model);

        assertThat(model.getAttribute("emptySearchResults"))
                .isEqualTo(Boolean.TRUE);
    }

    @Test
    public void searchReturnsModelWithNonEmptySearchResults() throws Exception {
        String theme = "new years";
        String songTitle = "aud lang syne";
        SongThemesController songThemesController = new SongThemesController(
                SongSearcher.withOneSong(new Song(theme, songTitle)));
        Model model = new ConcurrentModel();

        songThemesController.themeSearch("new years", model);

        assertThat(model.getAttribute("emptySearchResults"))
                .isEqualTo(Boolean.FALSE);
        List<SongView> searchResults = (List<SongView>) model.getAttribute("searchResults");
        assertThat(searchResults).containsExactly(new SongView("aud lang syne"));
    }
}
