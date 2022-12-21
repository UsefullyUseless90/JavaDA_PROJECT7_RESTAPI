package com.nnk.springboot.controllers;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.dao.Trade;
import com.nnk.springboot.domain.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TradeControllerTest {

    private MockMvc mvc;
    @MockBean
    private TradeService tService;
    @Autowired
    private WebApplicationContext context;

    private Trade trade;
    private Trade trade2;
    private Trade trade3;
    private List<Trade> tradeList = new ArrayList<>();

    private TradeDTO tDTO;
    private TradeDTO tDTO2;
    private TradeDTO tDTO3;
    private List<TradeDTO> tListDTO = new ArrayList<>();

    @BeforeEach
    void setup() {
        //Assign DAO
        trade = new Trade(01, "Account", "Type", 10d);
        trade2 = new Trade(02, "Account2", "Type2", 20d);
        trade3 = new Trade(03, "Account3", "Type3", 30d);
        tradeList = Arrays.asList(trade,trade2,trade3);
        //Assign DTO
        tDTO = new TradeDTO(trade);
        tDTO2 = new TradeDTO(trade2);
        tDTO3 = new TradeDTO(trade3);
        tListDTO= Arrays.asList(tDTO, tDTO2, tDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getTradeControllerTest() throws Exception {
        when(tService.findAll()).thenReturn(tradeList);
        mvc.perform(get("/trade/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("tradeList"))
        .andExpect(view().name("trade/list"));
    }

    @Test
    void addTradeFormControllerTest() throws Exception {
        mvc.perform(get("/trade/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("trade/add"));
    }

    @Test
    void validateTradeWithErrorControllerTest() throws Exception {
        when(tService.save(tDTO)).thenReturn(trade);
        mvc.perform(post("/trade/validate")
                        .sessionAttr("trade", trade)
                        .param("tradeId", "")
                        .param("account", trade.getAccount())
                        .param("type", trade.getType())
                        .param("buyQuantity", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("trade/add"));
    }

    @Test
    void validateTradeControllerTest() throws Exception {
        when(tService.save(tDTO)).thenReturn(trade);
        mvc.perform(post("/trade/validate")
                        .sessionAttr("trade", trade)
                        .param("account", trade.getAccount())
                        .param("type", trade.getType())
                        .param("buyQuantity", String.valueOf(trade.getBuyQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    void updateTradeControllerTest() throws Exception {
        when(tService.save(tDTO)).thenReturn(trade);
        mvc.perform(post("/trade/update/1")
                        .sessionAttr("trade", trade)
                        .param("account", trade.getAccount())
                        .param("type", trade.getType())
                        .param("buyQuantity", String.valueOf(trade.getBuyQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    void updateTradeWithErrorControllerTest() throws Exception {
        when(tService.save(tDTO)).thenReturn(trade);
        mvc.perform(post("/trade/update/1")
                        .sessionAttr("trade", trade)
                        .param("tradeId", "")
                        .param("account", trade.getAccount())
                        .param("type", trade.getType())
                        .param("buyQuantity", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/trade/update/{id}"));
    }

    @Test
    void updateFormTradeControllerTest() throws Exception {
        when(tService.findById(trade.getTradeId())).thenReturn(trade);
        mvc.perform(get("/trade/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("trade"))
        .andExpect(view().name("trade/update"));
    }

    @Test
    void deleteTradeControllerTest() throws Exception {
        when(tService.findById(trade.getTradeId())).thenReturn(trade);
        when(tService.delete(tDTO)).thenReturn(trade);
        mvc.perform(get("/trade/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/trade/list"));
    }
}
