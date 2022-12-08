package com.nstop.flow.engine.util;

import com.nstop.flow.engine.exception.ProcessException;

import java.util.Map;

public interface ExpressionCalculator {

    Boolean calculate(String expression, Map<String, Object> dataMap) throws ProcessException;
}
