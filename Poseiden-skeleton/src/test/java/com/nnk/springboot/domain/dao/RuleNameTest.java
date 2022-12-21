package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.RuleNameDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class RuleNameTest {

    @Test
    void testConstructor() {
        RuleName actualRuleName = new RuleName();
        assertNull(actualRuleName.getDescription());
        assertNull(actualRuleName.getTemplate());
        assertNull(actualRuleName.getSqlStr());
        assertNull(actualRuleName.getSqlPart());
        assertNull(actualRuleName.getName());
        assertNull(actualRuleName.getJson());
        assertEquals(0, actualRuleName.getId());
    }

    @Test
    void testConstructor2() {
        RuleName actualRuleName = new RuleName("Name", "The characteristics of someone or something", "Json", "Template",
                "Sql Str", "Sql Part");

        assertEquals("The characteristics of someone or something", actualRuleName.getDescription());
        assertEquals("Template", actualRuleName.getTemplate());
        assertEquals("Sql Str", actualRuleName.getSqlStr());
        assertEquals("Sql Part", actualRuleName.getSqlPart());
        assertEquals("Name", actualRuleName.getName());
        assertEquals("Json", actualRuleName.getJson());
        assertEquals(0, actualRuleName.getId());
    }

    @Test
    void testConstructor3() {
        RuleName actualRuleName = new RuleName(new RuleNameDTO(new RuleName()));
        assertNull(actualRuleName.getDescription());
        assertNull(actualRuleName.getTemplate());
        assertNull(actualRuleName.getSqlStr());
        assertNull(actualRuleName.getSqlPart());
        assertNull(actualRuleName.getName());
        assertNull(actualRuleName.getJson());
        assertEquals(0, actualRuleName.getId());
    }

}

