package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.BidList;
import com.nnk.springboot.domain.dto.BidListDTO;
import com.nnk.springboot.services.implementation.IBidListService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BidListController {
    @Autowired
    IBidListService bService;

   // private static final Logger logger = LogManager.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        log.info("Checklist of all the additions in the database");
        model.addAttribute("bidLists",bService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model)
    {
        log.info("Saving the bid into our database please wait");
        model.addAttribute(new BidList());
        return "bidList/add";
    }


    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if(result.hasErrors()){
            log.info("Result doesn't seem to be alright");
            return "bidList/add";
        }
        log.info("Saving the bid into our database please wait");
        bService.saveBid(bid);
        log.info("Checklist of all the additions in the database");
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
        if(result.hasErrors()){
            log.info("Bid seems wrong, please try again.");
            return "redirect:/bidList/update/{id}";
        }
        log.info("Updating in progress...");
        bService.update(new BidListDTO(bidList), id);
        log.info("Successfully updated!");
        model.addAttribute(bService.getAll());
        log.info("Checklist if update is missing");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("Looking for the bid to delete, please wait");
        BidList bList = bService.findById(id);
        log.info("Deleting the bid" + bList);
        bService.delete(bList);
        log.info("Bid list deleted...");
        List<BidList> list = bService.getAll();
        model.addAttribute(list);
        log.info("Check list if correct: " + list );
        return "redirect:/bidList/list";
    }
}
