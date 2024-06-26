package com.songthematic.songthemes.application;

import com.songthematic.songthemes.domain.Song;
import org.junit.jupiter.api.Disabled;
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
        List<Song> songRepository = new ArrayList<>();
        songRepository.add(new Song("Fire", "Baby's On Fire"));

        SongService songService = new SongService(songRepository);

        List<Song> songsFound = songService.searchByTheme("fire");
        assertThat(songsFound)
                .containsExactly(
                        new Song("Fire", "Baby's On Fire"));
    }

    @Test
    @Disabled
    void addedSongsAreSavedToRepository() throws Exception {
        List<Song> songRepository = new ArrayList<>();
        songRepository.add(new Song("Fire", "Baby's On Fire"));
        SongService songService = new SongService(songRepository);

        songService.addSong(new Song("Fire", "Smokestack Lightning"));

        assertThat(songRepository)
                .hasSize(2);
    }
}
