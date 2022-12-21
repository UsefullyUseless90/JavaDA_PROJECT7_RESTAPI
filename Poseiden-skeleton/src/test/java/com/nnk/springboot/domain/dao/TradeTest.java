package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.TradeDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class TradeTest {

    @Test
    void testConstructor() {
        Trade actualTrade = new Trade();
        assertNull(actualTrade.getAccount());
        assertNull(actualTrade.getType());
        assertNull(actualTrade.getTrader());
        assertEquals(0, actualTrade.getTradeId());
        assertNull(actualTrade.getTradeDate());
        assertNull(actualTrade.getStatus());
        assertNull(actualTrade.getSourceListId());
        assertNull(actualTrade.getSide());
        assertEquals(0.0d, actualTrade.getSellQuantity());
        assertEquals(0.0d, actualTrade.getSellPrice());
        assertNull(actualTrade.getSecurity());
        assertNull(actualTrade.getRevisionName());
        assertNull(actualTrade.getRevisionDate());
        assertNull(actualTrade.getDealType());
        assertNull(actualTrade.getDealName());
        assertNull(actualTrade.getCreationName());
        assertNull(actualTrade.getCreationDate());
        assertEquals(0.0d, actualTrade.getBuyQuantity());
        assertEquals(0.0d, actualTrade.getBuyPrice());
        assertNull(actualTrade.getBook());
        assertNull(actualTrade.getBenchmark());
    }

    @Test
    void testConstructor2() {
        Trade actualTrade = new Trade(123, "Type", "3", 10.0d);

        assertEquals("3", actualTrade.getAccount());
        assertEquals("Type", actualTrade.getType());
        assertNull(actualTrade.getTrader());
        assertEquals(123, actualTrade.getTradeId());
        assertNull(actualTrade.getTradeDate());
        assertNull(actualTrade.getStatus());
        assertNull(actualTrade.getSourceListId());
        assertNull(actualTrade.getSide());
        assertEquals(0.0d, actualTrade.getSellQuantity());
        assertEquals(0.0d, actualTrade.getSellPrice());
        assertNull(actualTrade.getSecurity());
        assertNull(actualTrade.getRevisionName());
        assertNull(actualTrade.getRevisionDate());
        assertNull(actualTrade.getDealType());
        assertNull(actualTrade.getDealName());
        assertNull(actualTrade.getCreationName());
        assertNull(actualTrade.getCreationDate());
        assertEquals(10.0d, actualTrade.getBuyQuantity());
        assertEquals(0.0d, actualTrade.getBuyPrice());
        assertNull(actualTrade.getBook());
        assertNull(actualTrade.getBenchmark());
    }

    @Test
    void testConstructor3() {
        Trade actualTrade = new Trade(new TradeDTO(new Trade()));
        assertNull(actualTrade.getAccount());
        assertNull(actualTrade.getType());
        assertEquals(0, actualTrade.getTradeId());
        assertEquals(0.0d, actualTrade.getBuyQuantity());
    }


}

