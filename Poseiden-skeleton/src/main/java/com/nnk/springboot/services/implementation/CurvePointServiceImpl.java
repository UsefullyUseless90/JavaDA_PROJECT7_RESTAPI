package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.dao.CurvePoint;
import com.nnk.springboot.domain.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CurvePointServiceImpl implements CurvePointService {

    @Autowired
    CurvePointRepository cPRepo;


    public List<CurvePoint> getAll(){
        List<CurvePoint> curvePoints = cPRepo.findAll();
        return curvePoints;
    }
    public CurvePoint findById(int id){
        CurvePoint cPoint = cPRepo.findById(id).orElse(null);
        return cPoint;
    }
    public CurvePoint saveCurve(CurvePointDTO curvePointDTO){
        CurvePoint cPoint = new CurvePoint(curvePointDTO);
        cPRepo.save(cPoint);
        return cPoint;
    }
    public void update(CurvePointDTO curvePointDTO){
        CurvePoint cPoint = cPRepo.findById(curvePointDTO.getId()).orElse(null);
        cPoint.setValue(curvePointDTO.getValue());
        cPoint.setCurveId(curvePointDTO.getCurvePointId());
        cPoint.setTerm(curvePointDTO.getTerm());
        cPRepo.save(cPoint);
    }
    public CurvePoint delete(CurvePoint curvePoint){
        CurvePoint cPoint = cPRepo.findById(curvePoint.getId()).orElse(null);
        cPRepo.delete(cPoint);
        return cPoint;
    }
}
