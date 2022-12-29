package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.dao.Trade;
import com.nnk.springboot.domain.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class ITradeService implements TradeService {

    @Autowired
    TradeRepository tRepo;

    public List<Trade> findAll(){
        List<Trade> tList = tRepo.findAll();
        return tList;
    }
    public Trade findById(int id){
        Trade trade = tRepo.findById(id).orElse(null);
        return trade;
    }
    public Trade save(TradeDTO tradeDTO){
        Trade trade = new Trade(tradeDTO);
        tRepo.save(trade);
        return trade;
    }

    public Trade update(TradeDTO tradeDTO, int id){
        Trade nTrade = tRepo.findById(id).orElse(null);
        nTrade.setType(tradeDTO.getType());
        nTrade.setAccount(tradeDTO.getAccount());
        nTrade.setBuyQuantity(tradeDTO.getBuyQuantity());
        tRepo.save(nTrade);
        return nTrade;
    }
    public Trade delete(TradeDTO tradeDTO){
        Trade trade = tRepo.findById(tradeDTO.getTradeId()).orElse(null);
        tRepo.delete(trade);
        return trade;
    }
}
