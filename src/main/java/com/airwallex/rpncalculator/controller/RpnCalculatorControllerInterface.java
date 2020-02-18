package com.airwallex.rpncalculator.controller;

import java.io.IOException;

public interface RpnCalculatorControllerInterface {

    enum Mode {
        commandline;
    }
    public void serve();

}
