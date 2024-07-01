package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;

import java.util.List;
import java.util.stream.Stream;

public class SongRepository {
    private final List<Song> songs;

    public SongRepository(List<Song> songList) {
        this.songs = songList;
    }

    static SongRepository create(List<Song> songList) {
        SongRepository songRepository = new SongRepository(songList);
        return songRepository;
    }

    public Stream<Song> allSongs() {
        return songs.stream();
    }

    void add(Song song) {
        this.songs.add(song);
    }
}