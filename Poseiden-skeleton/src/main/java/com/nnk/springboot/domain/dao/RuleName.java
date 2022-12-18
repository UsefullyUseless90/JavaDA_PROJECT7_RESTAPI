package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RuleNameDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    private int id;
    private String name;
    private String description;
    private String json;
    private String template;
    @Column(name = "sqlStr")
    private String sqlStr;
    @Column(name="sqlPart")
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public RuleName() {

    }

    public RuleName(RuleNameDTO ruleNameDTO) {
        this.id = ruleNameDTO.getId();
        this.name = ruleNameDTO.getName();
        this.description = ruleNameDTO.getDescription();
        this.json = ruleNameDTO.getJson();
        this.template = ruleNameDTO.getTemplate();
        this.sqlStr = ruleNameDTO.getSqlStr();
        this.sqlPart = ruleNameDTO.getSqlPart();
    }

}
