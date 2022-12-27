package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.BidList;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class BidListDTO {
    private int bListId;
    @NotEmpty
    @NotBlank(message = "Please specify account")
    private String account;
    @NotEmpty
    @NotBlank(message = "Please specify type")
    private String type;
    @Positive
    @NotBlank(message = "Quantity must be positive!")
    private double bidQuantity;

    public BidListDTO(BidList bidList) {
        this.bListId = bidList.getBidListId();
        this.account = bidList.getAccount();
        this.type = bidList.getType();
        this.bidQuantity = bidList.getBidQuantity();
    }
}
