package com.songthematic.songthemes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongSearcher {

    private final Map<String, Song> songs = new HashMap<>();

    private SongSearcher(Song... songs) {
        this.songs.put(songs[0].theme().toLowerCase(), songs[0]);
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
        Song foundSong = songs.get(requestedTheme.toLowerCase());

        if (foundSong != null) {
            return List.of(foundSong.title());
        }

        return Collections.emptyList();

    }

    private static SongSearcher createSongSearcher(Song... songs) {
        return new SongSearcher(songs[0]);
    }
}
