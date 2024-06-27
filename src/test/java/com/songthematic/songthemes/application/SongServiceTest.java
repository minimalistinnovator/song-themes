package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SongServiceTest {
    @Test
    void multipleSongsAddedIsFoundByItsTheme() throws Exception {
        SongService songService = new SongService();

        songService.addSong(new Song("new years", "This Will Be Our Year"));
        songService.addSong(new Song("new years", "Funky New Year"));

        List<Song> songsFound = songService.searchByTheme("new years");
        assertThat(songsFound)
                .containsExactly(
                        new Song("new years", "This Will Be Our Year"),
                        new Song("new years", "Funky New Year"));

    }

    @Test
    void savedSongsLoadedOnStartup() throws Exception {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Fire", "Baby's On Fire"));

        SongRepository songRepositoryNew = new SongRepository();
        songRepositoryNew.setSongRepository(songList);
        SongService songService = new SongService(songRepositoryNew);

        List<Song> songsFound = songService.searchByTheme("fire");
        assertThat(songsFound)
                .containsExactly(
                        new Song("Fire", "Baby's On Fire"));
    }

    @Test
    void addedSongsAreSavedToRepository() throws Exception {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Fire", "Baby's On Fire"));
        SongRepository songRepositoryNew = new SongRepository();
        songRepositoryNew.setSongRepository(songList);
        SongService songService = new SongService(songRepositoryNew);

        songService.addSong(new Song("Fire", "Smokestack Lightning"));

        assertThat(songRepositoryNew.getSongRepository())
                .hasSize(2);
    }
}
