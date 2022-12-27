package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.Trade;
import com.nnk.springboot.domain.dto.TradeDTO;

import java.util.List;

public interface TradeService {

    public List<Trade> findAll();

    public Trade findById(int id);

    public Trade save(TradeDTO tradeDTO);

    public Trade update(TradeDTO tradeDTO, int id);

    public Trade delete(TradeDTO tradeDTO);

}
