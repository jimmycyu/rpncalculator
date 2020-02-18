package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.controller.RpnCalculatorControllerFactory;
import com.airwallex.rpncalculator.controller.RpnCalculatorControllerInterface;


public class RpnCalculator {

    public static void main(String[] args) {
        RpnCalculatorControllerInterface interaction = RpnCalculatorControllerFactory.getInstance().getInteractionInterface();
        interaction.serve();

    }
}
