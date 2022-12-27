package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.RuleName;
import com.nnk.springboot.domain.dto.RuleNameDTO;

import java.util.List;

public interface RuleNameService {

    public List<RuleName> findAll();

    public RuleName findById(int id);

    public RuleName save(RuleNameDTO ruleNameDTO);

    public RuleName update(RuleNameDTO ruleNameDTO);

    public RuleName delete(RuleNameDTO ruleNameDTO);

}
