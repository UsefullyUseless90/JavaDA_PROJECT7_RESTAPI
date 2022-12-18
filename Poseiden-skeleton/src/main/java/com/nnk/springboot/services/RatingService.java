package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.Rating;
import com.nnk.springboot.domain.dto.RatingDTO;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RatingService {

    @Autowired
    RatingRepository reRepo;

    public List<Rating> getAll(){
        List<Rating> rList = reRepo.findAll();
        return rList;
    }
    public Rating findById(int id){
        Rating rating = reRepo.findById(id).orElse(null);
        return rating;
    }
    public Rating save(Rating rating){
        reRepo.save(rating);
        return rating;
    }

    public void update(RatingDTO ratingDto){
        Rating rating = reRepo.findById(ratingDto.getId()).orElse(null);
        rating.setMoodysRating(ratingDto.getMoodysRating());
        rating.setSandPRating(ratingDto.getSandPRating());
        rating.setFitchRating(ratingDto.getFitchRating());
        rating.setOrderNumber(ratingDto.getOrderNumber());
        reRepo.save(rating);
    }
    public Rating delete(Rating rating){
        Rating dRating = reRepo.findById(rating.getId()).orElse(null);
        reRepo.delete(dRating);
        return dRating;
    }
}
