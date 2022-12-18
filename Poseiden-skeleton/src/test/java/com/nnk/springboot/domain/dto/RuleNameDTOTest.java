package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.RuleName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleNameDTOTest {
    /**
     * Method under test: {@link RuleNameDTO#RuleNameDTO(RuleName)}
     */
    @Test
    void testConstructor() {
        RuleName ruleName = new RuleName();
        ruleName.setDescription("The characteristics of someone or something");
        ruleName.setId(1);
        ruleName.setJson("Json");
        ruleName.setName("Name");
        ruleName.setSqlPart("Sql Part");
        ruleName.setSqlStr("Sql Str");
        ruleName.setTemplate("Template");
        RuleNameDTO actualRuleNameDTO = new RuleNameDTO(ruleName);
        assertEquals("The characteristics of someone or something", actualRuleNameDTO.getDescription());
        assertEquals("Template", actualRuleNameDTO.getTemplate());
        assertEquals("Sql Str", actualRuleNameDTO.getSqlStr());
        assertEquals("Sql Part", actualRuleNameDTO.getSqlPart());
        assertEquals("Name", actualRuleNameDTO.getName());
        assertEquals("Json", actualRuleNameDTO.getJson());
        assertEquals(1, actualRuleNameDTO.getId());
    }
}

