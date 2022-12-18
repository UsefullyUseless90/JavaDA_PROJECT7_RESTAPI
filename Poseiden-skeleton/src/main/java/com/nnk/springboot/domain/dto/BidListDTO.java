package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.BidList;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class BidListDTO {
    private int bListId;
    private String account;
    private String type;
    @Positive
    private double bidQuantity;

    public BidListDTO(BidList bidList) {
        this.bListId = bidList.getBidListId();
        this.account = bidList.getAccount();
        this.type = bidList.getType();
        this.bidQuantity = bidList.getBidQuantity();
    }
}
