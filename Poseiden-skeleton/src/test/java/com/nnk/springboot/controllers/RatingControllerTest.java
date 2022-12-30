package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.Rating;
import com.nnk.springboot.domain.dto.RatingDTO;
import com.nnk.springboot.services.implementation.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RatingControllerTest {

    private MockMvc mvc;
    @MockBean
    public RatingServiceImpl RatingServiceImpl;
    @Autowired
    private WebApplicationContext context;

    Rating rating;
    Rating rating2;
    Rating rating3;
    private List<Rating> ratingList = new ArrayList<>();

    RatingDTO rDTO;
    RatingDTO rDTO2;
    RatingDTO rDTO3;
    private List<RatingDTO> rListDTO = new ArrayList<>();

    @BeforeEach
    void setup() {
        //Assign DAO
        rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating2 = new Rating("Moodys Rating2", "Sand PRating2", "Fitch Rating2", 20);
        rating3 = new Rating("Moodys Rating2", "Sand PRating3", "Fitch Rating3", 30);
        ratingList = Arrays.asList(rating, rating2, rating3);
        //Assign DTO
        rDTO = new RatingDTO(rating);
        rDTO2 = new RatingDTO(rating2);
        rDTO3 = new RatingDTO(rating3);
        rListDTO = Arrays.asList(rDTO, rDTO2, rDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllRatingControllerTest() throws Exception {
        when(RatingServiceImpl.getAll()).thenReturn(ratingList);
        mvc.perform(get("/rating/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("ratingList"))
        .andExpect(view().name("rating/list"));
    }

    @Test
    void addRatingFormControllerTest() throws Exception {
        mvc.perform(get("/rating/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("rating/add"));
    }

    @Test
    void validateRatingWithErrorControllerTest() throws Exception {
        when(RatingServiceImpl.save(rating)).thenReturn(rating);
        mvc.perform(post("/rating/validate")
                        .sessionAttr("rating", "")
                        .param("moodysRating", "")
                        .param("sandPRating", "")
                        .param("fitchRating", "")
                        .param("orderNumber", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("rating/add"));
    }

    @Test
    void validateRatingControllerTest() throws Exception {
        when(RatingServiceImpl.save(rating)).thenReturn(rating);
        mvc.perform(post("/rating/validate")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", String.valueOf(rating.getFitchRating()))
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    void updateRatingControllerTest() throws Exception {
        when(RatingServiceImpl.save(rating)).thenReturn(rating);
        mvc.perform(post("/rating/update/1")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", String.valueOf(rating.getFitchRating()))
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    void updateRatingErrorControllerTest() throws Exception {
        when(RatingServiceImpl.save(rating)).thenReturn(rating);
        mvc.perform(post("/rating/update/1")
                        .sessionAttr("rating", rating)
                        .param("moodysRating", "")
                        .param("sandPRating", "")
                        .param("fitchRating", "")
                        .param("orderNumber", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/rating/update/{id}"));
    }

    @Test
    void updateFormRatingControllerTest() throws Exception {
        rating.setId(01);
        when(RatingServiceImpl.findById(rating.getId())).thenReturn(rating);
        mvc.perform(get("/rating/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("rating"))
        .andExpect(view().name("rating/update"));
    }

    @Test
    void deleteRatingControllerTest() throws Exception {
        rating.setId(01);
        when(RatingServiceImpl.findById(rating.getId())).thenReturn(rating);
        when(RatingServiceImpl.delete(rating)).thenReturn(rating);
        mvc.perform(get("/rating/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/rating/list"));
    }

}
