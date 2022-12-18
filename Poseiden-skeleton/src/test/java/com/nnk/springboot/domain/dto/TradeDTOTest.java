package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.Trade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TradeDTOTest {
    /**
     * Method under test: {@link TradeDTO#TradeDTO(Trade)}
     */
    @Test
    void testConstructor() {
        Trade trade = new Trade();
        trade.setAccount("3");
        trade.setBenchmark("Benchmark");
        trade.setBook("Book");
        trade.setBuyPrice(10.0d);
        trade.setBuyQuantity(10.0d);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        trade.setCreationDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        trade.setCreationName("Creation Name");
        trade.setDealName("Deal Name");
        trade.setDealType("Deal Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        trade.setRevisionDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        trade.setRevisionName("Revision Name");
        trade.setSecurity("Security");
        trade.setSellPrice(10.0d);
        trade.setSellQuantity(10.0d);
        trade.setSide("Side");
        trade.setSourceListId("42");
        trade.setStatus("Status");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        trade.setTradeDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        trade.setTradeId(123);
        trade.setTrader("Trader");
        trade.setType("Type");
        TradeDTO actualTradeDTO = new TradeDTO(trade);
        assertEquals("3", actualTradeDTO.getAccount());
        assertEquals("Type", actualTradeDTO.getType());
        assertEquals(123, actualTradeDTO.getTradeId().intValue());
        assertEquals(10.0d, actualTradeDTO.getBuyQuantity());
    }
}

