package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Trade;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class TradeDTO {

    private Integer tradeId;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Please specify account")
    private String account;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Please specify type")
    private String type;
    @Positive(message = "Quantity must be positive!")
    private double buyQuantity;

    public TradeDTO(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.type = trade.getType();
        this.account = trade.getAccount();
        this.buyQuantity = trade.getBuyQuantity();
    }
}
