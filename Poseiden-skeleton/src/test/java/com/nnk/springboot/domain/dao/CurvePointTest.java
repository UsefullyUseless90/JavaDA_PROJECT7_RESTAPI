package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.CurvePointDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class CurvePointTest {

    @Test
    void testConstructor() {
        CurvePoint actualCurvePoint = new CurvePoint();
        assertNull(actualCurvePoint.getAsOfDate());
        assertEquals(0.0d, actualCurvePoint.getValue());
        assertEquals(0.0d, actualCurvePoint.getTerm());
        assertEquals(0, actualCurvePoint.getId());
        assertEquals(0, actualCurvePoint.getCurveId());
        assertNull(actualCurvePoint.getCreationDate());
    }

    @Test
    void testConstructor2() {
        CurvePoint actualCurvePoint = new CurvePoint(123, 10.0d, 10.0d);

        assertEquals(10.0d, actualCurvePoint.getValue());
        assertEquals(10.0d, actualCurvePoint.getTerm());
        assertEquals(123, actualCurvePoint.getCurveId());
    }

    @Test
    void testConstructor3() {
        CurvePoint actualCurvePoint = new CurvePoint(new CurvePointDTO(new CurvePoint()));
        assertEquals(0.0d, actualCurvePoint.getValue());
        assertEquals(0.0d, actualCurvePoint.getTerm());
        assertEquals(0, actualCurvePoint.getId());
        assertEquals(0, actualCurvePoint.getCurveId());
    }

}

