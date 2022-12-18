package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BidListService {

    @Autowired
    BidListRepository bRepo;

    public List<BidList> getAll(){
        List<BidList>bidLists = bRepo.findAll();
        return bidLists;
    }

    public BidList findById(int id){
        BidList bidList = bRepo.findById(id).orElse(null);
        return bidList;
    }
    public BidList saveBid(BidList bidList){
        bRepo.save(bidList);
    return bidList;
    }

    public BidList update(BidListDTO bidList, int id){
       BidList bList = bRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid with id :" + bidList.getBListId()));
        bList.setBid(id);
        bList.setAccount(bidList.getAccount());
        bList.setType(bidList.getType());
        bList.setBidQuantity(bidList.getBidQuantity());
        bRepo.save(bList);
        return bList;
    }

    public BidList delete(BidList bidList){
        bRepo.findById(bidList.getBidListId());
        bRepo.delete(bidList);
        return bidList;
    }

}
