package com.songthematic.songthemes;

import java.util.Collections;
import java.util.List;

public class SongSearcher {

    private final Song song;

    private SongSearcher(Song song) {
        this.song = song;
    }

    public static SongSearcher withNoSongs(String theme) {
        return createSongSearcher(new Song(
                theme,
                String.format("Songs with theme %s", theme)
        ));
    }

    public static SongSearcher withOneSong(Song song) {
        return createSongSearcher(song);
    }

    public static SongSearcher withMultipleSongs(List<Song> songs) {
        return createSongSearcher(songs.toArray(new Song[songs.size()]));
    }

    public List<String> byTheme(String requestedTheme) {

        if (requestedTheme.equalsIgnoreCase(this.song.theme())) {
            return List.of(song.title());
        }

        return Collections.emptyList();
    }

    private static SongSearcher createSongSearcher(Song... songs) {
        return new SongSearcher(songs[0]);
    }
}
