package com.songthematic.songthemes;

import java.util.Collections;
import java.util.List;

public class SongSearcher {

    private final String song;
    private final String theme;

    public SongSearcher(String theme, String song) {
        this.theme = theme;
        this.song = song;
    }

    public List<String> byTheme(String requestedTheme) {

        if (requestedTheme.equalsIgnoreCase(this.theme)) {
            return List.of(song);
        }

        return Collections.emptyList();
    }
}
