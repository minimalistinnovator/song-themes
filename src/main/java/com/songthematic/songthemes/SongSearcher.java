package com.songthematic.songthemes;

import java.util.Collections;
import java.util.List;

public class SongSearcher {

    private final String song;
    private final String theme;

    private SongSearcher(String theme, String song) {
        this.theme = theme;
        this.song = song;
    }

    public static SongSearcher withNoSongs(String theme) {
        return createSongSearcher(theme, String.format("Songs with theme %s", theme));
    }

    public static SongSearcher withOneSong(String theme, String song) {
        return createSongSearcher(theme, song);
    }

    public List<String> byTheme(String requestedTheme) {

        if (requestedTheme.equalsIgnoreCase(this.theme)) {
            return List.of(song);
        }

        return Collections.emptyList();
    }

    private static SongSearcher createSongSearcher(String theme, String song) {
        return new SongSearcher(theme, song);
    }
}
