package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.CurvePoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDTO {

    private int id;
    @NotNull
    @Positive
    private double value;
    @Positive
    private int curvePointId;
    @Positive
    private double term;

    public CurvePointDTO(CurvePoint curvePoint) {
        this.id = curvePoint.getId();
        this.curvePointId = curvePoint.getCurveId();
        this.value = curvePoint.getValue();
        this.term = curvePoint.getTerm();
    }
}
