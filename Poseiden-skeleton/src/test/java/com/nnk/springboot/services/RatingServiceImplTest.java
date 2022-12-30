package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.Rating;
import com.nnk.springboot.domain.dto.RatingDTO;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.implementation.RatingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RatingServiceImplTest {

    @InjectMocks
    private RatingServiceImpl rService;

    @Mock
    private RatingRepository rRepo;

    private List<Rating> rList;
    private Rating rating;
    private Rating rating2;
    private Rating rating3;

    private List<RatingDTO> rListDTO;
    private RatingDTO rDTO;
    private RatingDTO rDTO2;
    private RatingDTO rDTO3;

    @Before
    public void setUp() {
        //Assign DAO
        rating = new Rating( "Moody1", "Sandy1", "Fitch1", 1);
        rating2 = new Rating("Moody2", "Sandy2", "Fitch2", 2);
        rating3 = new Rating("Moody3", "Sandy3", "Fitch3", 3);
        rList = Arrays.asList(rating, rating2, rating3);
        //Assign DTO
        rDTO = new RatingDTO(rating);
        rDTO2 = new RatingDTO(rating2);
        rDTO3 = new RatingDTO(rating3);
        rListDTO = Arrays.asList(rDTO, rDTO2, rDTO3);
        // Initialising Service layer
        rService = new RatingServiceImpl(rRepo);
    }

    @Test
    public void getAll() {
        Mockito.when(rRepo.findAll()).thenReturn(rList);
        Assert.assertEquals(rList, rService.getAll());
    }

    @Test
    public void getById() {
        Mockito.when(rRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(rating));
        Assert.assertEquals(rating, rService.findById(1));
    }

    @Test
    public void save() {
        Rating saveTest = new Rating( "rate1", "Rating1", "Rating1", 1);
        rService.save(saveTest);
        verify(rRepo, times(1)).save(saveTest);
    }

    @Test
    public void update() {
        Mockito.when(rRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(rating));
        RatingDTO ratingUpdateTest = new RatingDTO(rating);
        rService.update(ratingUpdateTest);
        Rating ratingToUpdate = new Rating();
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setSandPRating(rating.getSandPRating());
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());
        Assert.assertEquals(ratingToUpdate.getMoodysRating(), ratingUpdateTest.getMoodysRating());
        Assert.assertEquals(ratingToUpdate.getSandPRating(),ratingUpdateTest.getSandPRating());
        Assert.assertEquals(ratingToUpdate.getFitchRating(), ratingUpdateTest.getFitchRating());
        Assert.assertEquals(ratingToUpdate.getOrderNumber(), ratingUpdateTest.getOrderNumber());
    }

    @Test
    public void delete() {
        Mockito.when(rRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(rating));
        rService.delete(rating);
        verify(rRepo, times(1)).delete(rating);
    }
}