package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.RuleName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RuleNameDTO {
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
    private String sqlStr;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message =  "Please specify sqlPart ")
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
