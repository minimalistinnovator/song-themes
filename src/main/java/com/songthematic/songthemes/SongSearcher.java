package com.songthematic.songthemes;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SongSearcher {

    private final Map<String, List<Song>> themeToSongsMap = new HashMap<>();

    private SongSearcher(Song... songs) {
        this.themeToSongsMap.putAll(Arrays
                .stream(songs)
                .collect(
                        Collectors.groupingBy(
                                song -> song.theme().toLowerCase(),
                                toList())));
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

    public static SongSearcher withMultipleSongs(Song... songs) {
        return createSongSearcher(songs);
    }

    public List<String> byTheme(String requestedTheme) {
        List<Song> matchingSongs = themeToSongsMap.get(requestedTheme.toLowerCase());

        if (matchingSongs != null) {
            return matchingSongs
                    .stream()
                    .map(Song::title)
                    .toList();
        }

        return Collections.emptyList();

    }

    private static SongSearcher createSongSearcher(Song... songs) {
        return new SongSearcher(songs);
    }
}
