package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.Rating;
import com.nnk.springboot.services.RatingService;
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
public class RatingController {
    @Autowired
    RatingService rService;

    private static final Logger logger = LogManager.getLogger(RatingController.class);

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        logger.info("Checking for all rating points");
        model.addAttribute(rService.getAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        logger.info("Adding rating to db");
        model.addAttribute(new Rating());
        logger.info("Rating added");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if(result.hasErrors()){
            logger.info("Fields seem to be incorrect, please try again...");
            return "rating/add";
        }
        rService.save(rating);
        logger.info("Saving rating in database...");
        model.addAttribute(rService.getAll());
        logger.info("Checklist of all rating additions in database: " + rService.getAll());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Checking if id is present in database...");
        model.addAttribute(rService.findById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            logger.info("Fiels are incorrect, please try again");
            return "redirect:/rating/update/{id}";
        }
        logger.info("Saving rating "+ id +"into database...");
        rService.save(rating);
        logger.info("Checklist of all ratings");
        model.addAttribute(rService.getAll());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.info("Looking for the curve to delete, please wait");
        Rating rating = rService.findById(id);
        logger.info("Deleting the rating" + rating);
        rService.delete(rating);
        logger.info("Curve point deleted...");
        List<Rating> rList = rService.getAll();
        model.addAttribute(rList);
        logger.info("Curve List if correct" + rList);
        return "redirect:/rating/list";
    }
}
