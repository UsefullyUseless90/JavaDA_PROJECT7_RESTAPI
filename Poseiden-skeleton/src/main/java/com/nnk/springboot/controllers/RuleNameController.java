package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.RuleName;
import com.nnk.springboot.domain.dto.RuleNameDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.implementation.IRuleNameService;
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
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    RuleNameRepository rnRepo;
    @Autowired
    IRuleNameService rnService;

    private static final Logger logger = LogManager.getLogger(RuleNameController.class);

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        logger.info("Checking for all RuleName, please wait...");
        model.addAttribute(rnRepo.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model)
    {
        logger.info("Adding rating to db ");
        model.addAttribute(new RuleName());
        logger.info("Rating successfully added");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
         logger.info("Data's not valid please retry");
            return "ruleName/add";
        }
        rnRepo.save(ruleName);
        logger.info("Saving Rating into database...");
        model.addAttribute(rnService.findAll());
        logger.info("Checklist of all the additions in the database");
        return "redirect:/ruleName/list";
    }
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("Checking if id is present in database...");
        model.addAttribute(rnService.findById(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("Fiels are incorrect, please try again");
            return "redirect:/ruleName/update/{id}";
        }
        logger.info("Saving rule names into database...");
        rnService.update(new RuleNameDTO(ruleName));
        logger.info("Checklist of all rule names");
        model.addAttribute("ruleName",rnRepo.findAll());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        logger.info("Looking for the rule name to delete, please wait");
        RuleName rName = rnService.findById(id);
        logger.info("Deleting the curve" + rName);
        RuleNameDTO ruleNameDTO = new RuleNameDTO(rName);
        rnService.delete(ruleNameDTO);
        logger.info("Curve point deleted...");
        List<RuleName> rNameList = rnService.findAll();
        model.addAttribute(rNameList);
        logger.info("Curve List if correct" + rNameList);
        return "redirect:/ruleName/list";
    }
}
