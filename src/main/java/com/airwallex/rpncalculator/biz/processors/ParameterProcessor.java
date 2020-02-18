package com.airwallex.rpncalculator.biz.processors;

import com.airwallex.rpncalculator.model.CalculatorStatus;

public interface ParameterProcessor<T> {

    enum Operators{
        add(2), substract(2), multiple(2),
        divide(2), sqrt(1), undo (0), clear (0), invalid(-1);
        int operatorCount;
        Operators(int operatorCount) {
            this.operatorCount = operatorCount;
        }
        public int getOperatorCount() {
            return this.operatorCount;
        }
        public static Operators parseEnum(String value){
            if(value.equals("+")) {
                return add;
            } else if(value.equals("-")){
                return substract;
            } else if(value.equals("*")) {
                return multiple;
            } else if(value.equals("/")) {
                return divide;
            } else if(value.equals("sqrt")) {
                return sqrt;
            } else if(value.equals("undo")) {
                return undo;
            } else if(value.equals("clear")) {
                return clear;
            }
            return invalid;

        }
    }

    public void acceptParameter(T parameter , CalculatorStatus stack);

    default boolean isValidOperator(Operators parameter, CalculatorStatus stack) {
        return true;
    }

}
