package com.songthematic.songthemes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongThemesControllerTest {

    @Test
    public void getSearchReturnsSearchResultsView() throws Exception {
        SongThemesController songThemesController = new SongThemesController();

        String viewName = songThemesController.themeSearch();
        assertThat(viewName).isEqualTo("theme-search-results");
    }
}
