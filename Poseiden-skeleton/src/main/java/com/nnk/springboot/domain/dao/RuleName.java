package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RuleNameDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    private int id;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify name")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify description")
    private String description;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify json")
    private String json;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify template")
    private String template;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please specify sqlStr")
    @Column(name = "sqlStr")
    private String sqlStr;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message =  "Please specify sqlPart ")
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
