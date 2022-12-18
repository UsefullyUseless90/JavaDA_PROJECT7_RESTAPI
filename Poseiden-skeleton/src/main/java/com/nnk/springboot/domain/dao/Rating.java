package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RatingDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    private int id;
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
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
