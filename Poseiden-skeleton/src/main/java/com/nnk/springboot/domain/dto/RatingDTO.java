package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Rating;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class RatingDTO {
    private int id;
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    @Positive
    private int orderNumber;

    public RatingDTO(Rating rating ) {
        this.id = rating.getId();
        this.moodysRating = rating.getMoodysRating();
        this.sandPRating = rating.getSandPRating();
        this.fitchRating = rating.getFitchRating();
        this.orderNumber = rating.getOrderNumber();
    }
}
