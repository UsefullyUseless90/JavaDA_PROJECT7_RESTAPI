package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Rating;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class RatingDTO {
    private int id;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify Moodys Rating")
    private String moodysRating;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify sandPRating")
    private String sandPRating;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify fitchRating ")
    private String fitchRating;
    @Positive(message = "Please specify the order number")
    private int orderNumber;

    public RatingDTO(Rating rating ) {
        this.id = rating.getId();
        this.moodysRating = rating.getMoodysRating();
        this.sandPRating = rating.getSandPRating();
        this.fitchRating = rating.getFitchRating();
        this.orderNumber = rating.getOrderNumber();
    }
}
