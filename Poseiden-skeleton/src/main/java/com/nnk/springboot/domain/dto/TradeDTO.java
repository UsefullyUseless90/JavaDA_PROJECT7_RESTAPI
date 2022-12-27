package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Trade;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class TradeDTO {

    private Integer tradeId;
    @NotEmpty
    @NotBlank(message = "Please specify account")
    private String account;
    @NotEmpty
    @NotBlank(message = "Please specify type")
    private String type;
    @Positive
    @NotBlank(message = "Quantity must be positive!")
    private double buyQuantity;

    public TradeDTO(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.type = trade.getType();
        this.account = trade.getAccount();
        this.buyQuantity = trade.getBuyQuantity();
    }
}
