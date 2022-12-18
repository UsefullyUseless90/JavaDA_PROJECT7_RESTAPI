package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.CurvePointDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    private int id;
    @Column(name = "curve_Id")
    private int curveId;
    private Date asOfDate;
    private double term;
    private double value;
    private Date creationDate;

    public CurvePoint(int curveId, double term , double value) {
        this.curveId = curveId;
        this.term= term;
        this.value = value;
    }

    public CurvePoint() {

    }

    public CurvePoint(CurvePointDTO curvePointDTO) {
        this.id = curvePointDTO.getId();
        this.curveId = curvePointDTO.getCurvePointId();
        this.value = curvePointDTO.getValue();
        this.term = curvePointDTO.getTerm();
    }
}
