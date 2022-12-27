package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.implementation.IBidListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBidListServiceTest {

    @InjectMocks
    public IBidListService IBidListService;

    @Mock
    public BidListRepository bRepos;

    private List<BidList> bLists;
    private BidList bidList;
    private BidList bidList2;
    private BidList bidList3;

    private List<BidListDTO> bidListDtos;
    private BidListDTO bListDTO;
    private BidListDTO bListDTO2;
    private BidListDTO bListDTO3;


    @Before
    public void setUp() {
        //Assign DAO
        bidList = new BidList(1, "First bidList", "Type 1", 20d);
        bidList2 = new BidList(2, "Second bidList", "Type 2", 10d);
        bidList3 = new BidList(3, "Third bidList", "Type 3", 5d);
        bLists = Arrays.asList(bidList, bidList2, bidList3);
        //Assign DTO
        bListDTO = new BidListDTO(bidList);
        bListDTO2 = new BidListDTO(bidList2);
        bListDTO3 = new BidListDTO(bidList3);
        bidListDtos = Arrays.asList(bListDTO, bListDTO2, bListDTO3);
        // Initialising Service layer
        IBidListService = new IBidListService(bRepos);
    }

    @Test
    public void getAllTest() {
        Mockito.when(bRepos.findAll()).thenReturn(bLists);
        Assert.assertEquals(bLists, IBidListService.getAll());
    }

    @Test
    public void findByIdTest() {
        Mockito.when(bRepos.findById(Mockito.anyInt())).thenReturn(Optional.of(bidList));
        Assert.assertEquals(bidList, IBidListService.findById(1));
    }

    @Test
    public void saveTest() {
        BidList bListTest = new BidList(1, "Account", "Type 1", 10d);
        IBidListService.saveBid(bListTest);
        verify(bRepos, times(1)).save(bListTest);
    }

    @Test
    public void updateTest() {
        Mockito.when(bRepos.findById(Mockito.anyInt())).thenReturn(Optional.of(bidList));
        BidList bList = new BidList(1, "New bidList", "Type 1", 20d);
        BidListDTO bidListDTO = new BidListDTO(bList);
        IBidListService.update(bidListDTO, 1);
        BidList updatedBList = new BidList(2, "Bid test", "test", 20d);
        Assert.assertEquals(updatedBList.getBidListId(), 2);
        Assert.assertEquals(updatedBList.getAccount(),"Bid test");
        Assert.assertEquals(updatedBList.getType(), "test");
    }

    @Test
    public void deleteTest() {
        Mockito.when(bRepos.findById(Mockito.anyInt())).thenReturn(Optional.of(bidList));
        bRepos.findById(bidList.getBidListId());
        bRepos.delete(bidList);
        Mockito.verify(bRepos, times(1)).delete(bidList);
    }
}
