package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.Trade;
import com.nnk.springboot.domain.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    @Autowired
    TradeService tService;

    private static final Logger logger = LogManager.getLogger(TradeController.class);

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute(tService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        logger.info("Adding trade to db");
        model.addAttribute(new Trade());
        logger.info("Trade added");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("Data's not valid please retry");
            return "trade/add";
        }
        tService.save(new TradeDTO(trade));
        logger.info("Saving trade in db...");
        model.addAttribute(tService.findAll());
        logger.info("Checklist of all the additions in the database");
        return "redirect:/trade/list"; // no idea of what's happening there...
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Checking if id is present in database...");
        model.addAttribute(tService.findById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("Fiels are incorrect, please try again");
            return "redirect:/trade/add";
        }
        logger.info("Saving trade into database...");
        tService.update(new TradeDTO(trade), id);
        logger.info("Checklist of all trades");
        model.addAttribute(tService.findAll());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.info("Looking for the trade to delete, please wait");
        Trade trade = tService.findById(id);
        logger.info("Deleting the trade" + trade);
        TradeDTO tDTO = new TradeDTO(trade);
        tService.delete(tDTO);
        logger.info("Trade deleted...");
        List<Trade> tList = tService.findAll();
        model.addAttribute(tList);
        logger.info("Curve List if correct" + tList);
        return "redirect:/trade/list";
    }
}
