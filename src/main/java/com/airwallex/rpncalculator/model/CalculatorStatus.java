package com.airwallex.rpncalculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.stream.Collectors;

public class CalculatorStatus {
    private static BigDecimal x;
    private Stack<BigDecimal> memoryStack;
  private Stack<Stack<BigDecimal>> logStack;

  public CalculatorStatus(){
      memoryStack = new Stack<BigDecimal>();
      logStack = new Stack<Stack<BigDecimal>>();
      logStack.push(new Stack<BigDecimal>());
  }

  public BigDecimal peek(){
      return memoryStack.peek();
  }

  public boolean hasEnoughOperationNumber(int count) {
      if(memoryStack.size() < count) {
          return false;
      }
      return true;
  }
  public void clear(){
      memoryStack.clear();
      logStack.push((Stack<BigDecimal>)memoryStack.clone());
  }
  public void undo(){
      if (logStack.size() != 1) {
          logStack.pop();
      }
      memoryStack = (Stack<BigDecimal>) logStack.peek().clone();
  }

  public void pushBack(BigDecimal bigDecimal){
      memoryStack.push(bigDecimal);
  }
  public void push(BigDecimal bigDecimal) {
      memoryStack.push(bigDecimal);
      logStack.push((Stack<BigDecimal>)memoryStack.clone());
  }

  public BigDecimal pop() {
      BigDecimal value= memoryStack.pop();
      return value;
  }

   public String getStatusAsString() {
     return this.memoryStack.stream().map(CalculatorStatus::apply).collect(Collectors.joining(" "));
   }

    private static String apply(BigDecimal x) {
        CalculatorStatus.x = x;
        final String s = x.setScale(10, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
        return s;
    }
}


