package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.BidList;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class BidListDTOTest {
    /**
     * Method under test: {@link BidListDTO#BidListDTO(BidList)}
     */
    @Test
    void testConstructor() {
        BidList bidList = new BidList();
        bidList.setAccount("3");
        bidList.setAsk(10.0d);
        bidList.setAskQuantity(10.0d);
        bidList.setBenchmark("Benchmark");
        bidList.setBid(10.0d);
        bidList.setBidListDate(mock(Date.class));
        bidList.setBidListId(1);
        bidList.setBidQuantity(10.0d);
        bidList.setBook("Book");
        bidList.setCommentary("Commentary");
        bidList.setCreationDate(mock(Date.class));
        bidList.setCreationName("Creation Name");
        bidList.setDealName("Deal Name");
        bidList.setDealType("Deal Type");
        bidList.setRevisionDate(mock(Date.class));
        bidList.setRevisionName("Revision Name");
        bidList.setSecurity("Security");
        bidList.setSide("Side");
        bidList.setSourceListId("42");
        bidList.setStatus("Status");
        bidList.setTrader("Trader");
        bidList.setType("Type");
        BidListDTO actualBidListDTO = new BidListDTO(bidList);
        assertEquals("3", actualBidListDTO.getAccount());
        assertEquals("Type", actualBidListDTO.getType());
        assertEquals(10.0d, actualBidListDTO.getBidQuantity());
        assertEquals(1, actualBidListDTO.getBListId());
    }

    /**
     * Method under test: {@link BidListDTO#BidListDTO(BidList)}
     */
    @Test
    void testConstructor2() {
        BidList bidList = new BidList();
        bidList.setAccount("3");
        bidList.setAsk(10.0d);
        bidList.setAskQuantity(10.0d);
        bidList.setBenchmark("Benchmark");
        bidList.setBid(10.0d);
        bidList.setBidListDate(mock(Date.class));
        bidList.setBidListId(1);
        bidList.setBidQuantity(10.0d);
        bidList.setBook("Book");
        bidList.setCommentary("Commentary");
        bidList.setCreationDate(mock(Date.class));
        bidList.setCreationName("Creation Name");
        bidList.setDealName("");
        bidList.setDealType("Deal Type");
        bidList.setRevisionDate(mock(Date.class));
        bidList.setRevisionName("Revision Name");
        bidList.setSecurity("Security");
        bidList.setSide("Side");
        bidList.setSourceListId("42");
        bidList.setStatus("Status");
        bidList.setTrader("Trader");
        bidList.setType("Type");
        BidListDTO actualBidListDTO = new BidListDTO(bidList);
        assertEquals("3", actualBidListDTO.getAccount());
        assertEquals("Type", actualBidListDTO.getType());
        assertEquals(10.0d, actualBidListDTO.getBidQuantity());
        assertEquals(1, actualBidListDTO.getBListId());
    }
}

