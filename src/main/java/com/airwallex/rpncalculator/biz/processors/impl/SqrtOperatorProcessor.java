package com.airwallex.rpncalculator.biz.processors.impl;

import com.airwallex.rpncalculator.biz.processors.ParameterProcessor;
import com.airwallex.rpncalculator.model.CalculatorStatus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.IllegalFormatPrecisionException;

public class SqrtOperatorProcessor implements ParameterProcessor<ParameterProcessor.Operators> {

    private static final String MINUS_VALUE_CANT_BE_SQRT = "%s can't be sqrt";

    @Override
    public boolean isValidOperator(Operators parameter, CalculatorStatus stack) {
        return stack.hasEnoughOperationNumber(parameter.getOperatorCount());
    }

    @Override
    public void acceptParameter(Operators parameter, CalculatorStatus stack) {
       BigDecimal value = stack.pop();
       if(value.compareTo(BigDecimal.ZERO) == 0) {
           stack.push(value);
           return;
       } else if(value.compareTo(BigDecimal.ZERO)< 0) {
           stack.pushBack(value);
           throw new IllegalArgumentException(String.format(MINUS_VALUE_CANT_BE_SQRT, value.toPlainString()));
       }
       BigDecimal value2  = BigDecimal.valueOf(2);
       int precision = 16;
       MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
       BigDecimal deviation = value;
       int cnt=0;
       while(cnt < precision) {
          deviation = deviation.add(value.divide(deviation, mc)).divide(value2, mc);
          cnt++;
       }
       stack.push(deviation);
    }
}
