package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RatingDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class RatingTest {
    /**
     * Method under test: {@link Rating#Rating()}
     */
    @Test
    void testConstructor() {
        Rating actualRating = new Rating();
        assertNull(actualRating.getFitchRating());
        assertNull(actualRating.getSandPRating());
        assertEquals(0, actualRating.getOrderNumber());
        assertNull(actualRating.getMoodysRating());
        assertEquals(0, actualRating.getId());
    }

    /**
     * Method under test: {@link Rating#Rating(String, String, String, int)}
     */
    @Test
    void testConstructor2() {
        Rating actualRating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        assertEquals("Fitch Rating", actualRating.getFitchRating());
        assertEquals("Sand PRating", actualRating.getSandPRating());
        assertEquals(10, actualRating.getOrderNumber());
        assertEquals("Moodys Rating", actualRating.getMoodysRating());
        assertEquals(0, actualRating.getId());
    }

    /**
     * Method under test: {@link Rating#Rating(RatingDTO)}
     */
    @Test
    void testConstructor3() {
        assertEquals(0, (new Rating(new RatingDTO(new Rating()))).getOrderNumber());
    }

}

