package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;
import com.songthematic.songthemes.domain.SongSearcher;

import java.util.ArrayList;
import java.util.List;

public class SongService {
    final SongRepository songRepository;
    SongSearcher songSearcher;

    public SongService() {
        songSearcher = SongSearcher.withNoSongs("blah");
        this.songRepository = new SongRepository(new ArrayList<>());
    }

    public SongService(SongRepository songRepository) {
        songSearcher = SongSearcher.createSongSearcher(songRepository.allSongs());
        this.songRepository = songRepository;
    }

    public List<Song> searchByTheme(String theme) {
        return songSearcher.byTheme(theme);
    }

    public void addSong(Song song) {
        songRepository.add(song);
        this.songSearcher = songSearcher.add(song);
    }

}
