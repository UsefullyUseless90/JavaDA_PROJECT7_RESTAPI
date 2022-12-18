package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.RuleName;
import lombok.Data;

@Data
public class RuleNameDTO {
    private int id;
    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;

    public RuleNameDTO(RuleName ruleName) {
        this.id = ruleName.getId();
        this.name = ruleName.getName();
        this.description = ruleName.getDescription();
        this.json = ruleName.getJson();
        this.template = ruleName.getTemplate();
        this.sqlStr = ruleName.getSqlStr();
        this.sqlPart = ruleName.getSqlPart();
    }
}
