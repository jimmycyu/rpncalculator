package com.airwallex.rpncalculator.biz.processors.impl;

import com.airwallex.rpncalculator.biz.processors.ParameterProcessor;
import com.airwallex.rpncalculator.model.CalculatorStatus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CommonOperatorsProcessor implements ParameterProcessor<ParameterProcessor.Operators> {

    private static final String ZERO_CANT_BE_DIVIDED = "0 can't be divided";

    public boolean isValidOperator(Operators parameter, CalculatorStatus stack) {
        return stack.hasEnoughOperationNumber(parameter.getOperatorCount());
    }

    @Override
    public void acceptParameter(Operators parameter, CalculatorStatus stack) {
        if(parameter == Operators.divide && BigDecimal.ZERO.compareTo(stack.peek()) == 0) {
            throw new IllegalArgumentException(ZERO_CANT_BE_DIVIDED);
        }
        BigDecimal value1 = stack.pop();
        BigDecimal value2  = stack.pop();
        switch(parameter) {
            case add:
                stack.push(value1.add(value2,new MathContext(15, RoundingMode.DOWN) ));
                break;
            case substract:
                stack.push(value2.subtract(value1,new MathContext(15, RoundingMode.DOWN)));
                break;
            case multiple:
                stack.push(value1.multiply(value2, new MathContext(15,  RoundingMode.DOWN)));
                break;
            case divide:
                stack.push(value2.divide(value1, new MathContext(15,  RoundingMode.DOWN)));
                break;
            default:
                break;



        }

    }
}
