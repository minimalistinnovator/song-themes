package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;
import com.songthematic.songthemes.domain.SongSearcher;

import java.util.List;

public class SongService {
    SongSearcher songSearcher;

    public SongService() {
        songSearcher = SongSearcher.withNoSongs("new years");
    }

    public List<Song> searchByTheme(String theme) {
        return songSearcher.byTheme(theme);
    }

    public void addSong(Song song) {
        this.songSearcher = SongSearcher.withOneSong(song);
    }
}
