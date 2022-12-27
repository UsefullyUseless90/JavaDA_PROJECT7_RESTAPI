package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;

import java.util.List;

public interface BidListService {

    public List<BidList> getAll();

    public BidList findById(int id);

    public BidList saveBid(BidList bidList);

    public BidList update(BidListDTO bidList, int id);

    public BidList delete(BidList bidList);
}
