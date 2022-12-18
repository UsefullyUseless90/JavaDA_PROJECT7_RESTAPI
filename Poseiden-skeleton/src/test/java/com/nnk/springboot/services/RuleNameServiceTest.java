package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.RuleName;
import com.nnk.springboot.domain.dto.RuleNameDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    private List<RuleName> ruleNameList;
    private RuleName ruleName;
    private RuleName ruleName2;
    private RuleName ruleName3;

    private List<RuleNameDTO> ruleNamesDTO;
    private RuleNameDTO ruleNameDTO;
    private RuleNameDTO ruleNameDTO2;
    private RuleNameDTO ruleNameDTO3;

    @Before
    public void setUp(){
        //Assign DAO
        ruleName = new RuleName( "Name1", "Description1", "Json", "Template", "SqlStr", "SqlPart");
        ruleName2 = new RuleName("Name2", "Description2", "Json", "Template", "SqlStr", "SqlPart");
        ruleName3 = new RuleName("Name3", "Description3", "Json", "Template", "SqlStr", "SqlPart");
        ruleNameList = Arrays.asList(ruleName, ruleName2, ruleName3);
        //Assign DTO
        ruleNameDTO = new RuleNameDTO(ruleName);
        ruleNameDTO2 = new RuleNameDTO(ruleName2);
        ruleNameDTO3 = new RuleNameDTO(ruleName3);
        ruleNamesDTO = Arrays.asList(ruleNameDTO, ruleNameDTO2, ruleNameDTO3);
        // Initialising Service layer
        ruleNameService = new RuleNameService(ruleNameRepository);
    }
    @Test
    public void getAll() {
        Mockito.when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        Assert.assertEquals(ruleNameList, ruleNameService.findAll());
    }


    @Test
    public void getById() {
        Mockito.when(ruleNameRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ruleName));
        Assert.assertEquals(ruleName, ruleNameService.findById(1));
    }

    @Test
    public void save() {
        RuleName ruleNameToSave = new RuleName( "Name1", "Description1", "Json", "Template", "SqlStr", "SqlPart");
        ruleNameService.save(new RuleNameDTO(ruleNameToSave));
        verify(ruleNameRepository, times(1)).save(ruleNameToSave);
    }


    @Test
    public void update() {
        Mockito.when(ruleNameRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ruleName));
        RuleNameDTO ruleNameUpdateTest = new RuleNameDTO(ruleName);
        ruleNameService.update(ruleNameUpdateTest);
        RuleName updatedRuleName = new RuleName();
        updatedRuleName.setName(ruleNameUpdateTest.getName());
        updatedRuleName.setDescription(ruleNameUpdateTest.getDescription());
        updatedRuleName.setJson(ruleNameUpdateTest.getJson());
        updatedRuleName.setTemplate(ruleNameUpdateTest.getTemplate());
        updatedRuleName.setSqlStr(ruleNameUpdateTest.getSqlStr());
        updatedRuleName.setSqlPart(ruleNameUpdateTest.getSqlPart());
        Assert.assertEquals(updatedRuleName.getName(), ruleNameUpdateTest.getName() );
        Assert.assertEquals(updatedRuleName.getDescription(), ruleNameUpdateTest.getDescription());
        Assert.assertEquals(updatedRuleName.getJson(), ruleNameUpdateTest.getJson());
        Assert.assertEquals(updatedRuleName.getTemplate(), ruleNameUpdateTest.getTemplate());
        Assert.assertEquals(updatedRuleName.getSqlStr(), ruleNameUpdateTest.getSqlStr());
        Assert.assertEquals(updatedRuleName.getSqlPart(), ruleNameUpdateTest.getSqlPart());
        verify(ruleNameRepository, times(1)).save(updatedRuleName);
    }

    @Test
    public void delete() {
        Mockito.when(ruleNameRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ruleName));
        ruleNameService.delete(new RuleNameDTO(ruleName));
        verify(ruleNameRepository, times(1)).delete(ruleName);
    }

}
