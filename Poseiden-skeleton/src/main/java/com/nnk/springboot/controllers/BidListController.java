package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {
    @Autowired
    BidListService bService;

    private static final Logger logger = LogManager.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("Checklist of all the additions in the database");
        model.addAttribute("bidLists",bService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model)
    {
        logger.info("Saving the bid into our database please wait");
        model.addAttribute(new BidList());
        return "bidList/add";
    }


    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if(result.hasErrors()){
            logger.info("Result doesn't seem to be alright");
            return "bidList/add";
        }
        logger.info("Saving the bid into our database please wait");
        bService.saveBid(bid);
        logger.info("Checklist of all the additions in the database");
        model.addAttribute(bService.getAll());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(bService.findById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if(result.hasErrors() || id != bidList.getBidListId()){
            logger.info("Bid seems wrong, please try again.");
            return "redirect:/bidList/add";
        }
        logger.info("Updating in progress...");
        bService.update(new BidListDTO(bidList), id);
        logger.info("Successfully updated!");
        model.addAttribute(bService.getAll());
        logger.info("Checklist if update is missing");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("Looking for the bid to delete, please wait");
        BidList bList = bService.findById(id);
        logger.info("Deleting the bid" + bList);
        bService.delete(bList);
        logger.info("Bid list deleted...");
        List<BidList> list = bService.getAll();
        model.addAttribute(list);
        logger.info("Check list if correct: " + list );
        return "redirect:/bidList/list";
    }
}
