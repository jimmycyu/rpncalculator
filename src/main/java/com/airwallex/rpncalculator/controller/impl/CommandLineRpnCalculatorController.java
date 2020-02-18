package com.airwallex.rpncalculator.controller.impl;

import com.airwallex.rpncalculator.RpnCalculator;
import com.airwallex.rpncalculator.biz.processors.LineProcessor;
import com.airwallex.rpncalculator.controller.RpnCalculatorControllerInterface;
import com.airwallex.rpncalculator.model.CalculatorStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandLineRpnCalculatorController implements RpnCalculatorControllerInterface {
    private CalculatorStatus calculatorStack = new CalculatorStatus();
    private LineProcessor lineProcessor = new LineProcessor();
    private static final String EXIT = "exit";

    @Override
    public void serve() {
        echo();
        String inputLine = "";
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader  br = new BufferedReader(is);
        try {
            while(true) {
                inputLine = br.readLine();
                if(inputLine != null) {
                inputLine = inputLine.trim();
                if (!inputLine.isEmpty()) {
                    if (EXIT.equalsIgnoreCase(inputLine.trim())) {
                        break;
                    }
                    lineProcessor.acceptCommandLine(inputLine+" ");
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br !=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void echo() {
         System.out.println("Welcome to commandline rpn calculator, type exit to exit");
    }
}
