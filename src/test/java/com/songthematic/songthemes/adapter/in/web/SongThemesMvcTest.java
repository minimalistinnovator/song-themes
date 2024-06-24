package com.songthematic.songthemes.adapter.in.web;

import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("io")
public class SongThemesMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getSearchEndpointReturns200() throws Exception {
        mockMvc.perform(get("/theme-search?requestedTheme=pants"))
                .andExpect(status().is2xxSuccessful());
    }
}
