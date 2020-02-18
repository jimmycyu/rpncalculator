package com.airwallex.rpncalculator.biz.processors;

import com.airwallex.rpncalculator.RpnCalculator;
import com.airwallex.rpncalculator.biz.processors.impl.*;
import com.airwallex.rpncalculator.model.CalculatorStatus;

import java.math.BigDecimal;
import java.math.MathContext;


public class LineProcessor {
    private CalculatorStatus status =  new CalculatorStatus();

    private DecimalProcessor decimalProcessor = new DecimalProcessor();

    private CommonOperatorsProcessor operatorProcessor = new CommonOperatorsProcessor();

    private SqrtOperatorProcessor sqrtOperatorProcessor = new SqrtOperatorProcessor();

    private UndoOperatorProcessor undoOperatorProcessor = new UndoOperatorProcessor();

    private ClearOperatorProcessor clearOperatorProcessor = new ClearOperatorProcessor();

    private static final String INSUFFICIENT_PARAM_ERROR_MSG = "operator %s (position:%d): insufficient parameters";

    private static final String NOT_A_VALID_PARAM = "not a valid parameter at position:%d";

    public static  final String  STATUS = "stack: %s";

    public String getStatus(){
        return this.status.getStatusAsString();
    }

    public void acceptCommandLine(String commandLine) {
       StringBuilder sb = new StringBuilder();
       for(int i=0;i<commandLine.length();i++){
           if(commandLine.charAt(i) == ' ') {
               if(sb.length() > 0 ) {
                   String value = sb.toString();
                   ParameterProcessor.Operators operator =  ParameterProcessor.Operators.parseEnum(value);
                   if(operator != ParameterProcessor.Operators.invalid) {
                       if(!operatorProcessor.isValidOperator(operator, status)) {
                           System.out.println(String.format(INSUFFICIENT_PARAM_ERROR_MSG,value , (i-value.length() + 1)));
                           break;
                       }
                       try {
                       switch(operator) {
                           case add:
                           case substract:
                           case multiple:
                           case divide:
                               operatorProcessor.acceptParameter(operator, status);
                               break;
                           case clear:
                               clearOperatorProcessor.acceptParameter(operator, status);
                               break;
                           case undo:
                               undoOperatorProcessor.acceptParameter(operator, status);
                               break;
                           case sqrt:
                               sqrtOperatorProcessor.acceptParameter(operator, status);
                               break;
                        }
                       } catch (IllegalArgumentException iae) {
                           System.out.println(iae.getMessage());
                           break;
                       }

                   } else {
                       try{
                          BigDecimal bigDecimal = new BigDecimal(value, new MathContext(15));
                          decimalProcessor.acceptParameter(bigDecimal, status);
                       } catch (NumberFormatException nfe) {
                           System.out.println(String.format (NOT_A_VALID_PARAM, i-value.length()+1));
                           break;
                       }

                   }
                   sb = new StringBuilder();
               } else {
                  continue;
               }
           } else {
               sb.append(commandLine.charAt(i));
           }

       }
       System.out.println(String.format(STATUS, status.getStatusAsString()));

    }


}
