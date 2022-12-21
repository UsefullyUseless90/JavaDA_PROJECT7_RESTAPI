package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RatingDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    private int id;
    @NotEmpty
    private String moodysRating;
    @NotEmpty
    private String sandPRating;
    @NotEmpty
    private String fitchRating;
    @Positive
    private int orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating() {

    }

    public Rating(RatingDTO ratingDTO) {
        this.orderNumber = ratingDTO.getOrderNumber();
    }
}
