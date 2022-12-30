package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.CurvePoint;
import com.nnk.springboot.domain.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.implementation.CurvePointServiceImpl;
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
public class CurvePointServiceImplTest {

    @InjectMocks
    public CurvePointServiceImpl cPS;

    @Mock
    public CurvePointRepository cPRepo;

    private List<CurvePoint> cPLists;
    private CurvePoint cPoint;
    private CurvePoint cPoint2;
    private CurvePoint cPoint3;

    private List<CurvePointDTO> cPListDTO;
    private CurvePointDTO cPDTO;
    private CurvePointDTO cPDTO2;
    private CurvePointDTO cPDTO3;


    @Before
    public void setUp() {
        //Assign DAO
        cPoint = new CurvePoint(1,  10d, 20d);
        cPoint2 = new CurvePoint(2,  10d, 10d);
        cPoint3 = new CurvePoint(3,  10.5, 0.0);
        cPLists = Arrays.asList(cPoint, cPoint2, cPoint3);
        //Assign DTO
        cPDTO = new CurvePointDTO(cPoint);
        cPDTO2 = new CurvePointDTO(cPoint2);
        cPDTO3 = new CurvePointDTO(cPoint3);
        cPListDTO = Arrays.asList(cPDTO, cPDTO2, cPDTO3);
        // Initialising Service layer
        cPS = new CurvePointServiceImpl(cPRepo);
    }

    @Test
    public void getAllTest() {
        Mockito.when(cPRepo.findAll()).thenReturn(cPLists);
        Assert.assertEquals(cPLists, cPS.getAll());
    }

    @Test
    public void findByIdTest() {
        Mockito.when(cPRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(cPoint));
        Assert.assertEquals(cPoint, cPS.findById(1));
    }

    @Test
    public void saveTest() {
        CurvePoint cPointTest = new CurvePoint(cPDTO);
        cPS.saveCurve(new CurvePointDTO(cPointTest));
        verify(cPRepo, times(1)).save(cPointTest);
    }

    @Test
    public void updateTest() {
        Mockito.when(cPRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(cPoint));
        CurvePoint curvePoint = new CurvePoint(cPDTO);
        CurvePointDTO curvePointDTO = new CurvePointDTO(curvePoint);
        cPS.update(curvePointDTO);
        CurvePoint updatedCP = new CurvePoint(curvePointDTO);
        verify(cPRepo,times(1)).save(updatedCP);
    }

    @Test
    public void deleteTest() {
        Mockito.when(cPRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(cPoint));
        cPRepo.findById(cPoint.getCurveId());
        cPRepo.delete(cPoint);
        Mockito.verify(cPRepo, times(1)).delete(cPoint);
    }

}
