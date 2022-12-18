package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.CurvePoint;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;

class CurvePointDTOTest {
    /**
     * Method under test: {@link CurvePointDTO#CurvePointDTO(CurvePoint)}
     */
    @Test
     void testConstructor() {
        CurvePoint curvePoint = new CurvePoint();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        curvePoint.setAsOfDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        curvePoint.setCreationDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        curvePoint.setCurveId(123);
        curvePoint.setId(1);
        curvePoint.setTerm(10.0d);
        curvePoint.setValue(10.0d);
        CurvePointDTO actualCurvePointDTO = new CurvePointDTO(curvePoint);
        assertEquals(123, actualCurvePointDTO.getCurvePointId());
        assertEquals(10.0d, actualCurvePointDTO.getValue());
        assertEquals(10.0d, actualCurvePointDTO.getTerm());
        assertEquals(1, actualCurvePointDTO.getId());
    }
}

