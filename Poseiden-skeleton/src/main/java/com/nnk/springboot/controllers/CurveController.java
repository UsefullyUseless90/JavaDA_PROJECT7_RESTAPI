package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.CurvePoint;
import com.nnk.springboot.domain.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {
    @Autowired
    CurvePointService cPS;

    private static final Logger logger = LogManager.getLogger(CurveController.class);

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
    logger.info("Checking for all curve points");
    model.addAttribute(cPS.getAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model)
    {
    logger.info("Adding curve point to db");
    model.addAttribute(new CurvePoint());
    logger.info("Curve point added");
    return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(result.hasErrors()){
            logger.info("Data's not valid please retry");
            return "curvePoint/add";
        }
        cPS.saveCurve(new CurvePointDTO(curvePoint));
        logger.info("Saving curve in db...");
        model.addAttribute(cPS.getAll());
        logger.info("Checklist of all the additions in the database");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Checking if id is present in database...");
        model.addAttribute(cPS.findById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if(result.hasErrors() || id != curvePoint.getId()){
            logger.info("Fiels are incorrect, please try again");
            return "redirect:/curvePoint/add";
        }
        logger.info("Saving curve into database...");
        cPS.saveCurve(new CurvePointDTO(curvePoint));
        logger.info("Checklist of all curves");
        model.addAttribute(cPS.getAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("Looking for the curve to delete, please wait");
        CurvePoint cPoint = cPS.findById(id);
        logger.info("Deleting the curve" + cPoint);
        cPS.delete(cPoint);
        logger.info("Curve point deleted...");
        List<CurvePoint> cPointList = cPS.getAll();
        model.addAttribute(cPointList);
        logger.info("Curve List if correct" + cPointList);
        return "redirect:/curvePoint/list";
    }
}
