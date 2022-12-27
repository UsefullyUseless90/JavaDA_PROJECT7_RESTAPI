package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.Rating;
import com.nnk.springboot.domain.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    List<Rating> getAll();

    Rating findById(int id);

    Rating save(Rating rating);

    void update(RatingDTO ratingDTO);

    Rating delete(Rating rating);

}
