package com.airwallex.rpncalculator.biz.processors.impl;

import com.airwallex.rpncalculator.biz.processors.ParameterProcessor;
import com.airwallex.rpncalculator.model.CalculatorStatus;

import java.math.BigDecimal;

public class DecimalProcessor implements ParameterProcessor<BigDecimal>  {

    @Override
    public void acceptParameter(BigDecimal param, CalculatorStatus stack) {
        stack.push(param);
    }

}
