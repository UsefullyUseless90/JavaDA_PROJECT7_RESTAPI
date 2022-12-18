package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {

    private MockMvc mvc;
    @MockBean
    private BidListService bService;
    @Autowired
    private WebApplicationContext context;

    private BidList bid;
    private BidList bid2;
    private BidList bid3;
    private List<BidList> bLists = new ArrayList<>();

    private List<BidListDTO>bListsDTO = new ArrayList<>();
    private BidListDTO bDTO;
    private BidListDTO bDTO2;
    private BidListDTO bDTO3;

    @Before
    public void setup() {
        //Assign DAO
        bid = new BidList(01,"Account", "Type", 10d);
        bid2 = new BidList(02,"Account2", "Type2", 20d);
        bid3 = new BidList(03,"Account3", "Type3", 30d);
        bLists = Arrays.asList(bid, bid2, bid3);
        //Assign DTO
        bDTO = new BidListDTO(bid);
        bDTO2 = new BidListDTO(bid2);
        bDTO3 = new BidListDTO(bid3);
        bListsDTO = Arrays.asList(bDTO,bDTO2,bDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllBidListControllerTest() throws Exception {
        when(bService.getAll()).thenReturn(bLists);
        mvc.perform(get("/bidList/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("bidLists"))
        .andExpect(view().name("bidList/list"));
    }

    @Test
    public void addBidFormControllerTest() throws Exception {
        mvc.perform(get("/bidList/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("bidList/add"));
    }

    @Test
    public void validateBidListWithErrorControllerTest() throws Exception {
        when(bService.saveBid(bid)).thenReturn(bid);
        mvc.perform(post("/bidList/validate")
                       // .sessionAttr("bidList", bid)
                        .param("bidListId", "")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", String.valueOf(-10))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("bidList/add"));
    }

    @Test
    public void validateBidListControllerTest() throws Exception {
        when(bService.saveBid(bid)).thenReturn(bid);
        mvc.perform(post("/bidList/validate")
                        .sessionAttr("bidList", bid)
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", String.valueOf(bid.getBidQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void updateBidListControllerTest() throws Exception {
        when(bService.saveBid(bid)).thenReturn(bid);
        bid.setBidListId(01);
        mvc.perform(post("/bidList/update/1")
                        .sessionAttr("bidList", bid)
                        .param("bidListId", String.valueOf(bid.getBidListId()))
                        .param("account", bid.getAccount())
                        .param("type", bid.getType())
                        .param("bidQuantity", String.valueOf(bid.getBidQuantity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void updateBidListWithErrorControllerTest() throws Exception {
        when(bService.saveBid(bid)).thenReturn(bid);
        mvc.perform(post("/bidList/update/2")
                        .param("bidListId", "")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
        .andExpect(view().name("redirect:/bidList/add"));
    }

    @Test
    public void updateFormBidListControllerTest() throws Exception {
        when(bService.findById(bid.getBidListId())).thenReturn(bid);
        mvc.perform(get("/bidList/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("bidList"))
        .andExpect(view().name("bidList/update"));
    }

    @Test
    public void deleteBidListControllerTest() throws Exception {
        when(bService.findById(bid.getBidListId())).thenReturn(bid);
        when(bService.delete(bid)).thenReturn(bid);
        mvc.perform(get("/bidList/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/bidList/list"));
    }
}

