package com.nnk.springboot.domain.dao;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.dto.BidListDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@SpringBootTest(classes = Application.class)

public class BidListTest {

    @Test
    public void testConstructor() {
        BidList actualBidList = new BidList();
        assertNull(actualBidList.getAccount());
        assertNull(actualBidList.getType());
        assertNull(actualBidList.getTrader());
        assertNull(actualBidList.getStatus());
        assertNull(actualBidList.getSourceListId());
        assertNull(actualBidList.getSide());
        assertNull(actualBidList.getSecurity());
        assertNull(actualBidList.getRevisionName());
        assertNull(actualBidList.getRevisionDate());
        assertNull(actualBidList.getDealType());
        assertNull(actualBidList.getDealName());
        assertNull(actualBidList.getCreationName());
        assertNull(actualBidList.getCreationDate());
        assertNull(actualBidList.getCommentary());
        assertNull(actualBidList.getBook());
        assertEquals(0.0d, actualBidList.getBidQuantity());
        assertEquals(0, actualBidList.getBidListId());
        assertNull(actualBidList.getBidListDate());
        assertEquals(0.0d, actualBidList.getBid());
        assertNull(actualBidList.getBenchmark());
        assertEquals(0.0d, actualBidList.getAskQuantity());
        assertEquals(0.0d, actualBidList.getAsk());
    }

    @Test
    public void testConstructor2() {
        BidList actualBidList = new BidList(1, "3", "Type", 10.0d);

        assertEquals("3", actualBidList.getAccount());
        assertEquals("Type", actualBidList.getType());
        assertNull(actualBidList.getTrader());
        assertNull(actualBidList.getStatus());
        assertNull(actualBidList.getSourceListId());
        assertNull(actualBidList.getSide());
        assertNull(actualBidList.getSecurity());
        assertNull(actualBidList.getRevisionName());
        assertNull(actualBidList.getRevisionDate());
        assertNull(actualBidList.getDealType());
        assertNull(actualBidList.getDealName());
        assertNull(actualBidList.getCreationName());
        assertNull(actualBidList.getCreationDate());
        assertNull(actualBidList.getCommentary());
        assertNull(actualBidList.getBook());
        assertEquals(10.0d, actualBidList.getBidQuantity());
        assertEquals(1, actualBidList.getBidListId());
        assertNull(actualBidList.getBidListDate());
        assertEquals(0.0d, actualBidList.getBid());
        assertNull(actualBidList.getBenchmark());
        assertEquals(0.0d, actualBidList.getAskQuantity());
        assertEquals(0.0d, actualBidList.getAsk());
    }

    @Test
    public void testConstructor3() {
        BidList actualBidList = new BidList(new BidListDTO(new BidList()));
        assertNull(actualBidList.getAccount());
        assertNull(actualBidList.getType());
        assertEquals(0.0d, actualBidList.getBidQuantity());
        assertEquals(0, actualBidList.getBidListId());
    }

}

