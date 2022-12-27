package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.RuleName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class RuleNameDTO {
    private int id;
    @NotEmpty
    @NotBlank(message = "Please specify name")
    private String name;
    @NotEmpty
    @NotBlank(message = "Please specify description")
    private String description;
    @NotEmpty
    @NotBlank(message = "Please specify json")
    private String json;
    @NotEmpty
    @NotBlank(message = "Please specify template")
    private String template;
    @NotEmpty
    @NotBlank(message = "Please specify sqlStr")
    private String sqlStr;
    @NotEmpty
    @NotBlank(message = "Please specify sqlPart ")
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
