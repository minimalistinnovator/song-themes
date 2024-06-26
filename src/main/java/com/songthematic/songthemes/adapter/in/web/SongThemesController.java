package com.songthematic.songthemes.adapter.in.web;

import com.songthematic.songthemes.domain.SongSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongThemesController {

    private final SongSearcher songSearcher;

    @Autowired
    public SongThemesController(SongSearcher searcher) {
        this.songSearcher = searcher;
    }

    @GetMapping("/theme-search")
    public String themeSearch(@RequestParam String requestedTheme, Model model) {
        List<String> foundSongs = songSearcher.songTitlesByTheme(requestedTheme);
        List<SongView> songViews = foundSongs
                .stream()
                .map(SongView::new)
                .toList();

        if (songViews.isEmpty()) {
            return "theme-search-no-results";
        } else {
            model.addAttribute("searchResults", songViews);
            return "theme-search-has-results";
        }
    }
}
