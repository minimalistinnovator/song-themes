package com.songthematic.songthemes;

import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

public class SongThemesControllerTest {

    @Test
    public void searchReturnsSearchResultsView() throws Exception {
        SongThemesController songThemesController = new SongThemesController(SongSearcher.withSongsForTheme("new years"));
        Model model = new ConcurrentModel();

        String viewName = songThemesController.themeSearch(model);

        assertThat(viewName).isEqualTo("theme-search-results");
    }

    @Test
    public void searchReturnsModelWithNoSongsFoundAttribute() throws Exception {
        SongThemesController songThemesController = new SongThemesController(SongSearcher.withSongsForTheme("new years"));
        Model model = new ConcurrentModel();

        songThemesController.themeSearch(model);

        assertThat(model.getAttribute("emptySearchResults"))
                .isEqualTo(Boolean.TRUE);
    }
}
