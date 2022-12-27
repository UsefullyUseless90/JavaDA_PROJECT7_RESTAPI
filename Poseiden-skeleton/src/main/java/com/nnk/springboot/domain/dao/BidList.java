package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.BidListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.sql.Date;
@NoArgsConstructor
@Entity
@Data
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @Column(name = "bid_list_id")
    private int bidListId;
    @NotEmpty
    @NotBlank(message = "Please specify account")
    @Column(name = "account")
    private String account;
    @NotEmpty
    @NotBlank(message = "Please specify type")
    @Column(name = "type")
    private String type;
    @Positive
    @NotBlank(message = "Quantity must be positive!")
    @Column(name = "bidQuantity")
    private double bidQuantity;
    @Column(name = "askQuantity")
    private double askQuantity;
    @Column(name = "bid")
    private double bid;
    @Column(name = "ask")
    private double ask;
    @Column(name = "benchmark")
    private String benchmark;
    @Column(name = "bidListDate")
    private Date bidListDate;
    @Column(name = "commentary")
    private String commentary;
    @Column(name = "security")
    private String security;
    @Column(name = "status")
    private String status;
    @Column(name = "trader")
    private String trader;
    @Column(name = "book")
    private String book;
    @Column(name = "creationName")
    private String creationName;
    @Column(name = "creationDate")
    private Date creationDate;
    @Column(name = "revisionName")
    private String revisionName;
    @Column(name = "revisionDate")
    private Date revisionDate;
    @Column(name = "dealName")
    private String dealName;
    @Column(name = "dealType")
    private String dealType;
    @Column(name = "sourceListId")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    public BidList(Integer bidListId, String account, String type, double bidQuantity) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }


    public BidList(BidListDTO dto) {
        this.setBidListId(dto.getBListId());
        this.setAccount(dto.getAccount());
        this.setType(dto.getType());
        this.setBidQuantity(dto.getBidQuantity());

    }
}
