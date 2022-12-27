package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.TradeDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @Column(name = "trade_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeId;
    @NotEmpty
    @NotBlank(message = "Please specify account")
    private String account;
    @NotEmpty
    @NotBlank(message = "Please specify type")
    private String type;
    @Positive(message = "Quantity must be positive!")
    private double buyQuantity;
    private double sellQuantity;
    private double buyPrice;
    private double sellPrice;
    private Date tradeDate;
    private String security;
    private String status;
    private String trader;
    private String benchmark;
    private String book;
    private String creationName;
    private Date creationDate;
    private String revisionName;
    private Date revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Trade(int tradeId, String type, String account, Double buyQuantity) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    public Trade() {

    }

    public Trade(TradeDTO tradeDTO) {
        this.tradeId = tradeDTO.getTradeId();
        this.type = tradeDTO.getType();
        this.account = tradeDTO.getAccount();
        this.buyQuantity = tradeDTO.getBuyQuantity();
    }
}
