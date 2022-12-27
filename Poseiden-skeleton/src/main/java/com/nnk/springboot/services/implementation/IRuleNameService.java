package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.dao.RuleName;
import com.nnk.springboot.domain.dto.RuleNameDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class IRuleNameService implements RuleNameService {

    @Autowired
    RuleNameRepository rnRepo;

    public List<RuleName> findAll(){
        List<RuleName> ruleNameList = rnRepo.findAll();
        return ruleNameList;
    }
    public RuleName findById(int id){
        RuleName rName = rnRepo.findById(id).orElse(null);
        return rName;
    }
    public RuleName save(RuleNameDTO ruleNameDTO){
        RuleName rName = new RuleName(ruleNameDTO);
        rnRepo.save(rName);
        return rName;
    }

    public RuleName update(RuleNameDTO ruleNameDTO){
        RuleName rName = rnRepo.findById(ruleNameDTO.getId()).orElse(null);
        rName.setId(ruleNameDTO.getId());
        rName.setName(ruleNameDTO.getName());
        rName.setDescription(ruleNameDTO.getDescription());
        rName.setJson(ruleNameDTO.getJson());
        rName.setTemplate(ruleNameDTO.getTemplate());
        rName.setSqlStr(ruleNameDTO.getSqlStr());
        rName.setSqlPart(ruleNameDTO.getSqlPart());
        rnRepo.save(rName);
        return rName;
    }
    public RuleName delete(RuleNameDTO ruleNameDTO){
        RuleName rName = rnRepo.findById(ruleNameDTO.getId()).orElse(null);
        rnRepo.delete(rName);
        return rName;
    }
}
