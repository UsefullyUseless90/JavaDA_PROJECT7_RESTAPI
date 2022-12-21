package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RuleNameDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String json;
    @NotEmpty
    private String template;
    @NotEmpty
    @Column(name = "sqlStr")
    private String sqlStr;
    @NotEmpty
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
