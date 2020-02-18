package com.airwallex.rpncalculator.biz.processors.impl;

import com.airwallex.rpncalculator.biz.processors.ParameterProcessor;
import com.airwallex.rpncalculator.model.CalculatorStatus;

public class UndoOperatorProcessor implements ParameterProcessor<ParameterProcessor.Operators> {
    @Override
    public void acceptParameter(Operators parameter, CalculatorStatus stack) {
        stack.undo();

    }
}
