package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.CurvePoint;
import com.nnk.springboot.domain.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CurvePointService cPService;
    @Autowired
    private WebApplicationContext context;

    private CurvePointDTO cDTO;
    private CurvePointDTO cDTO2;
    private CurvePointDTO cDTO3;
    private List<CurvePointDTO> cListsDTO;

    private CurvePoint cPoint;
    private CurvePoint cPoint2;
    private CurvePoint cPoint3;
    private List<CurvePoint> cLists;



    @BeforeEach
    public void setUp() {
        //Assign DAO
        cPoint = new CurvePoint( 5, 10d, 5d);
        cPoint2 = new CurvePoint( 3, 10d, 10d);
        cPoint3 = new CurvePoint( 10, 10d, 50d);
        cLists = Arrays.asList(cPoint, cPoint2, cPoint3);
        //Assign DTO
        cDTO = new CurvePointDTO(cPoint);
        cDTO2 = new CurvePointDTO(cPoint2);
        cDTO3= new CurvePointDTO(cPoint3);
        cListsDTO = Arrays.asList(cDTO, cDTO2, cDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllCurvePointControllerTest() throws Exception {
        when(cPService.getAll()).thenReturn(cLists);
        mvc.perform(get("/curvePoint/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("curvePointList"))
        .andExpect(view().name("curvePoint/list"));
    }

    @Test
    void addCurveFormControllerTest() throws Exception {
        mvc.perform(get("/curvePoint/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateCurvePointWithErrorControllerTest() throws Exception {
        when(cPService.saveCurve(cDTO)).thenReturn(cPoint);
        mvc.perform(post("/curvePoint/validate")
                        .param("curveId", "")
                        .param("term", String.valueOf(cPoint.getTerm()))
                        .param("value", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateCurvePointControllerTest() throws Exception {
        when(cPService.saveCurve(cDTO)).thenReturn(cPoint);
        mvc.perform(post("/curvePoint/validate")
                        .sessionAttr("curvePoint", cPoint)
                        .param("curveId", String.valueOf(cPoint.getCurveId()))
                        .param("term", String.valueOf(cPoint.getTerm()))
                        .param("value", String.valueOf(cPoint.getValue()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    void UpdateCurvePointControllerTest() throws Exception {
        when(cPService.saveCurve(cDTO)).thenReturn(cPoint);
        mvc.perform(post("/curvePoint/update/1")
                        .sessionAttr("curvePoint", cPoint)
                        .param("curveId", String.valueOf(cPoint.getCurveId()))
                        .param("term", String.valueOf(cPoint.getTerm()))
                        .param("value", String.valueOf(cPoint.getValue()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    void updateCurvePointWithErrorControllerTest() throws Exception {
        when(cPService.saveCurve(cDTO)).thenReturn(cPoint);
        mvc.perform(post("/curvePoint/update/1")
                        .sessionAttr("curvePoint", cPoint)
                        .param("curveId", "")
                        .param("term", String.valueOf(cPoint.getTerm()))
                        .param("value", "-10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/curvePoint/update/{id}"));
    }

    @Test
    void updateFormCurvePointControllerTest() throws Exception {
        when(cPService.findById(cPoint.getCurveId())).thenReturn(cPoint);
        mvc.perform(get("/curvePoint/update/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("curvePoint"))
        .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void deleteCurvePointControllerTest() throws Exception {
        when(cPService.findById(cPoint.getCurveId())).thenReturn(cPoint);
        when(cPService.saveCurve(cDTO)).thenReturn(cPoint);
        mvc.perform(get("/curvePoint/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/curvePoint/list"));
    }
}
