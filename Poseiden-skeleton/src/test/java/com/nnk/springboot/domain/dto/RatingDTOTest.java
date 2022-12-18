package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Rating;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class RatingDTOTest {
    /**
     * Method under test: {@link RatingDTO#RatingDTO(Rating)}
     */
    @Test
    void testConstructor() {
        Rating rating = new Rating();
        rating.setFitchRating("Fitch Rating");
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setOrderNumber(10);
        rating.setSandPRating("Sand PRating");
        RatingDTO actualRatingDTO = new RatingDTO(rating);
        assertEquals("Fitch Rating", actualRatingDTO.getFitchRating());
        assertEquals("Sand PRating", actualRatingDTO.getSandPRating());
        assertEquals(10, actualRatingDTO.getOrderNumber());
        assertEquals("Moodys Rating", actualRatingDTO.getMoodysRating());
        assertEquals(1, actualRatingDTO.getId());
    }
}

