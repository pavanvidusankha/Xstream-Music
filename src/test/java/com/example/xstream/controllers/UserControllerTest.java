package com.example.xstream.controllers;

import com.example.xstream.models.User;
import com.example.xstream.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    UserServiceImpl userService;

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect((ResultMatcher) jsonPath("$.id").value(1L))
//               .andExpect((ResultMatcher) jsonPath("$.uname").value("psam"))
//              .andExpect((ResultMatcher) jsonPath("$.email").value("psam@xtream.com"));
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1")
//                .contentType(MediaType.APPLICATION_JSON);

//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse());
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void registerNewUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new User("ahales", "alex", "holder", "aholder@gmail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test

    void deleteUser() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/users/{id}", 2) )
                .andExpect(status().isAccepted());

    }

    @Test
    void updateUser() throws Exception {

        String patchInJson = "{\"lname\":\"Holder\"}";
//asJsonString(new User("alex1", "Alex", "Holder", "alex@gmail.com")
        mockMvc.perform( MockMvcRequestBuilders
                .patch("/users/{id}", 2)
                .content(patchInJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("Holder"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("Alex"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alex@gmail.com"));
    }

    @Test
    void putUser() throws Exception {

//        User updatedUser = User.builder().id(2).fname("Alex").lname("Holder").email("alex@gmail.com").build();

        //mockito
//        Mockito.when(use.findById(RECORD_1.getPatientId())).thenReturn(Optional.of(RECORD_1));
//        Mockito.when(patientRecordRepository.save(updatedRecord)).thenReturn(updatedRecord);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/users/{id}", 2)
                .content(asJsonString(new User(null,"Alex","Holder","alex@gmail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("Alex"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("Holder"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alex@gmail.com"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}