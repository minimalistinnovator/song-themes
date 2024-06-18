package com.songthematic.songthemes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SongThemesController {

    private final SongSearcher songSearcher;

    @Autowired
    public SongThemesController(SongSearcher searcher) {
        this.songSearcher = searcher;
    }

    @GetMapping("/theme-search")
    public String themeSearch(Model model) {
        List<String> foundSongs = songSearcher.byTheme("");
        model.addAttribute("emptySearchResults", foundSongs.isEmpty());
        return "theme-search-results";
    }
}
