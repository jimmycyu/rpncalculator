package com.airwallex.rpncalculator.controller;

import com.airwallex.rpncalculator.controller.impl.CommandLineRpnCalculatorController;

public class RpnCalculatorControllerFactory {

    private static final RpnCalculatorControllerFactory INSTANCE = new RpnCalculatorControllerFactory();

    private RpnCalculatorControllerFactory(){

    }

    public static RpnCalculatorControllerFactory getInstance() {
        return INSTANCE;
    }

    public RpnCalculatorControllerInterface getInteractionInterface() {
        return getInteractionInterface(RpnCalculatorControllerInterface.Mode.commandline);
    }


    public RpnCalculatorControllerInterface getInteractionInterface(RpnCalculatorControllerInterface.Mode mode) {
        switch(mode) {
            default:
                return new CommandLineRpnCalculatorController();
        }
     }

}
