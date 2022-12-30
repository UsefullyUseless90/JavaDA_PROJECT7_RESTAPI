package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.Trade;
import com.nnk.springboot.domain.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.implementation.TradeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TradeServiceImplTest {

    @InjectMocks
    private TradeServiceImpl TradeServiceImpl;

    @Mock
    private TradeRepository tradeRepository;

    private List<Trade> tList;
    private Trade trade;
    private Trade trade2;
    private Trade trade3;

    private List<TradeDTO> tListDTO;
    private TradeDTO tDTO;
    private TradeDTO tDTO2;
    private TradeDTO tDTO3;

    @Before
    public void setUp() {
        //Assign DAO
        trade = new Trade(1, "trade1", "Type 1", 10.0);
        trade2 = new Trade(2, "trade2", "Type 2", 20.0);
        trade3 = new Trade(3, "trade3", "Type 3", 30.0);
        tList = Arrays.asList(trade, trade2, trade3);
        //Assign DTO
        tDTO = new TradeDTO(trade);
        tDTO2 = new TradeDTO(trade2);
        tDTO3 = new TradeDTO(trade3);
        tListDTO = Arrays.asList(tDTO, tDTO2, tDTO3);
        // Initialising Service layer
        TradeServiceImpl = new TradeServiceImpl(tradeRepository);
    }

    @Test
    public void getAll() {
        Mockito.when(tradeRepository.findAll()).thenReturn(tList);
        Assert.assertEquals(tList, TradeServiceImpl.findAll());
    }

    @Test
    public void getById() {
        Mockito.when(tradeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(trade));
        Assert.assertEquals(trade, TradeServiceImpl.findById(1));
    }

    @Test
    public void save() {
        Trade tradeToSave = new Trade(1, "trade1", "Type 1", 10.0);
        TradeServiceImpl.save(tDTO);
        verify(tradeRepository, times(1)).save(tradeToSave);
    }

    @Test
    public void update() {
        Mockito.when(tradeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(trade));
        TradeDTO tradeUpdateTest = new TradeDTO(trade);
        TradeServiceImpl.update(tradeUpdateTest, trade.getTradeId());
        Trade tradeUpdated = new Trade(1, "trade1", "Type 1", 10.0);
        verify(tradeRepository, times(1)).save(tradeUpdated);
    }

    @Test
    public void delete() {
        Mockito.when(tradeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(trade2));
        TradeDTO tradeToDelete = new TradeDTO(trade2);
        TradeServiceImpl.delete(tradeToDelete);
        verify(tradeRepository, times(1)).delete(new Trade(tradeToDelete));
    }
}
