package com.nstop.flow.engine.util;

import com.alibaba.fastjson.JSONObject;
import com.nstop.flow.engine.exception.ProcessException;

import java.util.Map;

public interface ExpressionCalculator {

    Boolean calculate(String expression, JSONObject dataMap) throws ProcessException;
}
