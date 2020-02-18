package com.airwallex.rpncalculator.tests;

import com.airwallex.rpncalculator.biz.processors.LineProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class SampleTests {

    private static LineProcessor lineProcessor = new LineProcessor();

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                /***below are corner cases**/
                {"clear undo undo", ""}, {"clear 0 sqrt", "0"},
                {"clear -5 sqrt", "-5"}, {"clear 5 0 /", "5 0"},
                {"clear 5 aa","5"}, {"clear aa 5", ""},
                /**below are sample cases */
                {"clear 5 2", "5 2"}, {"clear 2 sqrt", "1.4142135623"},
                {"clear 9 sqrt", "3"}, {"clear 5 2 -", "3"},
                {"3 -", "0"}, {"clear", ""},
                {"5 4 3 2", "5 4 3 2"},{"undo undo *", "20"},
                {"5 *", "100"}, {"undo", "20 5"},
                {"clear 7 12 2 /", "7 6"}, {"*", "42"},
                {"4 /", "10.5"}, {"clear 1 2 3 4 5", "1 2 3 4 5"},
                {"*", "1 2 3 20"}, {"clear 3 4 -", "-1"},
                {"clear 1 2 3 4 5", "1 2 3 4 5"}, {"* * * *", "120"},
                {"clear 1 2 3 * 5 + * * 6 5", "11"}
        });
    }


    private String commandLineInput;
    private String status;

    public SampleTests(String commandLineInput, String status) {
        this.commandLineInput = commandLineInput;
        this.status = status;
    }

    @Test
    public void test(){
        lineProcessor.acceptCommandLine(commandLineInput+ " ");
        Assert.assertEquals(lineProcessor.getStatus(), status);
    }








}
