package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.dao.RuleName;
import com.nnk.springboot.domain.dto.RuleNameDTO;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    private MockMvc mvc;
    @MockBean
    private RuleNameService rService;
    @Autowired
    private WebApplicationContext context;

    private RuleNameDTO rDTO;
    private RuleNameDTO rDTO2;
    private RuleNameDTO rDTO3;
    private List<RuleNameDTO> DTOList = new ArrayList<>();

    private RuleName rName;
    private RuleName rName2;
    private RuleName rName3;
    private List<RuleName> rNList = new ArrayList<>();

    // Don't understand why it doesn't work....
    /*
    @BeforeEach
    void setup() {
        //Assign DAO
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rName2 = new RuleName("Rule Name2", "Description2", "Json2", "Template2", "SQL2", "SQL Part2");
        rName3 = new RuleName("Rule Name3", "Description3", "Json3", "Template3", "SQL3", "SQL Part3");
        rName.setId(05);
        rName2.setId(06);
        rName3.setId(07);
        rNList = Arrays.asList(rName, rName2, rName3);
        //Assign DTO
        rDTO = new RuleNameDTO(rName);
        rDTO2 = new RuleNameDTO(rName2);
        rDTO3 = new RuleNameDTO(rName3);
        DTOList = Arrays.asList(rDTO, rDTO2, rDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
 */
    @Test
    void getAllRuleNameControllerTest() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rNList.add(rName);
        //Rest of the test part
        when(rService.findAll()).thenReturn(rNList);
        mvc.perform(get("/ruleName/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("ruleNameList"))
        .andExpect(view().name("ruleName/list"));
    }

    @Test
    void addRuleNameFormControllerTest() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rNList.add(rName);
        //Rest of the test part
        mvc.perform(get("/ruleName/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateRuleNameWithErrorControllerTest() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rNList.add(rName);
        rDTO = new RuleNameDTO(rName);
        DTOList.add(rDTO);
        //Rest of the test part
        when(rService.save(rDTO)).thenReturn(rName);
        mvc.perform(post("/ruleName/validate")
                        .param("id", "")
                        .param("name", "")
                        .param("description", "")
                        .param("json", "")
                        .param("template", "")
                        .param("sqlStr", "")
                        .param("sqlPart", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateRuleNameController() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rNList.add(rName);
        rDTO = new RuleNameDTO(rName);
        DTOList.add(rDTO);
        //Rest of the test part
        when(rService.save(rDTO)).thenReturn(rName);
        mvc.perform(post("/ruleName/validate")
                        .sessionAttr("ruleName", rName)
                        .param("name", rName.getName())
                        .param("description", rName.getDescription())
                        .param("json", rName.getJson())
                        .param("template", rName.getTemplate())
                        .param("sqlStr", rName.getSqlStr())
                        .param("sqlPart", rName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    void updateRuleNameController() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rDTO = new RuleNameDTO(rName);
        //Rest of the test part
        when(rService.save(rDTO)).thenReturn(rName);
        mvc.perform(post("/ruleName/update/5")
                        .sessionAttr("ruleName", rName)
                        .param("name", rName.getName())
                        .param("description", rName.getDescription())
                        .param("json", rName.getJson())
                        .param("template", rName.getTemplate())
                        .param("sqlStr", rName.getSqlStr())
                        .param("sqlPart", rName.getSqlStr())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    void updateRuleNameWithErrorController() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rName.setId(5);
        rDTO = new RuleNameDTO(rName);
        //Rest of the test part
        when(rService.save(rDTO)).thenReturn(rName);
        mvc.perform(post("/ruleName/update/5")
                        .sessionAttr("ruleName", rName)
                        .param("id", "")
                        .param("name", "")
                        .param("description", "null")
                        .param("json", "")
                        .param("template", "")
                        .param("sqlStr", "")
                        .param("sqlPart", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/ruleName/add"));
    }

    @Test
    void updateFormRuleNameControllerTest() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rName.setId(05);
        rDTO = new RuleNameDTO(rName);
        //Rest of the test part
        when(rService.findById(rName.getId())).thenReturn(rName);
        mvc.perform(get("/ruleName/update/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("ruleName"))
        .andExpect(view().name("ruleName/update"));
    }

    @Test
    void deleteRuleNameControllerTest() throws Exception {
        //The @BeforeEach section, does not work so initialising everything needed here.
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        rName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rName.setId(05);
        rDTO = new RuleNameDTO(rName);
        //Rest of the test part
        when(rService.findById(rName.getId())).thenReturn(rName);
        when(rService.delete(rDTO)).thenReturn(rName);
        mvc.perform(get("/ruleName/delete/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/ruleName/list"));
    }
}
