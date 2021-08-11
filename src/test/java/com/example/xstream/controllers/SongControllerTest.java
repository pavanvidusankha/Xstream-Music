package com.example.xstream.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SongController.class)
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSong() throws Exception {
        //MockMvcRequestBuilders.get()
       // mockMvc.perform(get("/songs")).andExpect(content().toString())
        //mockMvc.perform(post("/students")).andExpect(status().is2xxSuccessful());
        mockMvc.perform(get("/songs")).andDo(print()).andExpect(status().isOk());
                //.andExpect(content().string(containsString("the weeknd")));
    }

    @Test
    void getSongByArtistId() {
    }

    @Test
    void getSongs() {
    }

    @Test
    void registerNewSong() {
    }

    @Test
    void deleteSong() {
    }

    @Test
    void updateSong() {
    }

    @Test
    void putSong() {
    }
}