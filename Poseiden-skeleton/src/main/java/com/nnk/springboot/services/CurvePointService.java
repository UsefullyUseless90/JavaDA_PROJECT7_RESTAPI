package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.CurvePoint;
import com.nnk.springboot.domain.dto.CurvePointDTO;

import java.util.List;

public interface CurvePointService {

    public List<CurvePoint> getAll();

    public CurvePoint findById(int id);

    public CurvePoint saveCurve(CurvePointDTO curvePointDTO);

    public void update(CurvePointDTO curvePointDTO);

    public CurvePoint delete(CurvePoint curvePoint);
}
