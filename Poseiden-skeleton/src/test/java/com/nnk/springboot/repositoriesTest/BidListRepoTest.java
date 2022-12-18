
package com.nnk.springboot.repositoriesTest;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BidListRepoTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTest() {
        BidList bidList = new BidList(1,"Account Test", "Type Test", 10d);

        // Save
        bidList = bidListRepository.save(bidList);
        Assert.assertNotNull(bidList.getBidListId());
        Assert.assertTrue(bidList.getBidQuantity() == 10d);

        // Update
        bidList.setBidQuantity(20d);
        bidList = bidListRepository.save(bidList);
        Assert.assertTrue(bidList.getBidQuantity() == 20d);

        // Find
        List<BidList> listResult = bidListRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = bidList.getBidListId();
        bidListRepository.delete(bidList);
        Optional<BidList> bidOptional = bidListRepository.findById(id);
        Assert.assertFalse(bidOptional.isPresent());
    }
}