package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Trade;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class TradeDTO {

    private Integer tradeId;
    private String account;
    private String type;
    @Positive
    private double buyQuantity;

    public TradeDTO(Trade trade) {
        this.tradeId = trade.getTradeId();
        this.type = trade.getType();
        this.account = trade.getAccount();
        this.buyQuantity = trade.getBuyQuantity();
    }
}
