package com.songthematic.songthemes.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SongSearcher {

    private final Map<String, List<Song>> themeToSongsMap = new HashMap<>();

    private SongSearcher(Song... songs) {
        this.themeToSongsMap.putAll(Arrays
                .stream(songs)
                .collect(
                        Collectors.groupingBy(
                                song -> song.themes().stream().findFirst().orElse("").toLowerCase(),
                                toList())));
    }

    public static SongSearcher createSongSearcher(Stream<Song> songs) {
        return new SongSearcher(songs.toArray(Song[]::new));
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

    public List<String> songTitlesByTheme(String requestedTheme) {
        List<Song> matchingSongs = themeToSongsMap.get(requestedTheme.toLowerCase());

        if (matchingSongs != null) {
            return matchingSongs
                    .stream()
                    .map(Song::title)
                    .toList();
        }

        return Collections.emptyList();

    }

    public List<Song> byTheme(String requestedTheme) {
        return themeToSongsMap.getOrDefault(
                requestedTheme.toLowerCase(),
                Collections.emptyList());
    }

    private static SongSearcher createSongSearcher(Song... songs) {
        return new SongSearcher(songs);
    }

    public SongSearcher add(Song song) {
        Stream<Song> songStream = this.themeToSongsMap
                .values()
                .stream()
                .flatMap(Collection::stream);

        Song[] songs = Stream
                .concat(songStream, Stream.of(song))
                .toArray(Song[]::new);

        return withMultipleSongs(songs);
    }
}
