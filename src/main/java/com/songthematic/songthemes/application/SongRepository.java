package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;

import java.util.List;
import java.util.stream.Stream;

public class SongRepository {
    List<Song> songs;

    public SongRepository() {
    }

    static SongRepository create(List<Song> songList) {
        SongRepository songRepository = new SongRepository();
        songRepository.setSongs(songList);
        return songRepository;
    }

    public Stream<Song> allSongs() {
        return songs.stream();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    void add(Song song) {
        this.songs.add(song);
    }
}