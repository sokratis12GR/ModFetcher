package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.Math.*;

public class CommandCalculate extends CommandUser {

    private String calcTypeOne = "";
    private String calcTypeTwo = "";
    private String calcTypeThree = "";
    private double[] numbers = new double[]{0, 0, 0, 0};
    private Number calculated;

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        String messageError = "Please use ***$calc <number_one> <type> <number_two>...***";
        double[] numbersIn = numbers;

        if (args.length > 1) {
            if (!Pattern.matches(".*[a-zA-Z].*", args[1])) {
                numbersIn[0] = Double.valueOf(args[1]);
                if (args.length > 2) {
                    calcTypeOne = args[2];
                    description = "Result: " + getCalculations();
                    if (args.length > 3) {
                        numbersIn[1] = Double.valueOf(args[3]);
                        doCalculations(false, false);
                        description = "Result: " + getCalculations();
                        if (args.length > 4) {
                            calcTypeTwo = args[4];
                            description = "Result: " + getCalculations();
                            if (args.length > 5) {
                                numbersIn[2] = Double.valueOf(args[5]);
                                doCalculations(true, false);
                                description = "Result: " + getCalculations();
                                if (args.length > 6) {
                                    calcTypeThree = args[6];
                                    description = "Result: " + getCalculations();
                                    if (args.length > 7) {
                                        numbersIn[3] = Double.valueOf(args[7]);
                                        doCalculations(true, true);
                                        description = "Result: " + getCalculations();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            sendMessage(message.getChannel(), messageError);
        }
        sendMessage(message.getChannel(), description);
    }

    private Number getCalculations() {
        return calculated;
    }

    private void doCalculations(boolean secondCalc, boolean thirdCalc) {
        Number result = 0;
        Number numberOne = 0;
        double first = numbers[0];
        double second = numbers[1];
        Number numberTwo = 0;
        double third = numbers[2];
        Number numberThree = 0;
        double four = numbers[3];
        numberOne = calcSwitch(calcTypeOne, first, second);
        result = numberOne;
        if (secondCalc) {
            numberTwo = calcSwitch(calcTypeTwo, numberOne, third);
            result = numberTwo;
            if (thirdCalc) {
                numberThree = calcSwitch(calcTypeThree, numberTwo, four);
                result = numberThree;
            }
        }
        calculated = result;
    }

    private static Number calcSwitch(String calc, Number one, double two) {
        Number result = 0;
        Number calculated = 0;
        switch (calc) {
            case "+":
                calculated = (double) one + two;
                result = calculated;
                break;
            case "add":
                calculated = (double) one + two;
                result = calculated;
                break;
            case "plus":
                calculated = (double) one + two;
                result = calculated;
                break;
            case "-":
                calculated = (double) one - two;
                result = calculated;
                break;
            case "minus":
                calculated = (double) one - two;
                result = calculated;
                break;
            case "sub":
                calculated = (double) one - two;
                result = calculated;
                break;
            case "*":
                calculated = (double) one * two;
                result = calculated;
                break;
            case "multiply":
                calculated = (double) one * two;
                result = calculated;
                break;
            case "times":
                calculated = (double) one * two;
                result = calculated;
                break;
            case "/":
                calculated = (double) one / two;
                result = calculated;
                break;
            case "divide":
                calculated = (double) one / two;
                result = calculated;
                break;
            case "^":
                calculated = pow((double) one, two);
                result = calculated;
                break;
            case "%":
                calculated = (double) one % two;
                result = calculated;
                break;
            case "modulus":
                calculated = (double) one % two;
                result = calculated;
                break;
            case "√":
                calculated = pow((double) one, 1 / two);
                result = calculated;
                break;
            case "root":
                calculated = pow((double) one, 1 / two);
                result = calculated;
                break;
            case "+e":
                calculated = addExact((long) one, (long) two);
                result = calculated;
                break;
            case "-e":
                calculated = subtractExact((long) one, (long) two);
                result = calculated;
                break;
            case "*e":
                calculated = multiplyExact((long) one, (long) two);
                result = calculated;
                break;
            case "max":
                calculated = max((double) one, two);
                result = calculated;
                break;
            case "min":
                calculated = min((double) one, two);
                result = calculated;
                break;
            case "floorMod":
                calculated = floorMod((long) one, (long) two);
                result = calculated;
                break;
            case "floorDiv":
                calculated = floorDiv((long) one, (long) two);
                result = calculated;
                break;
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "Does calculations.\nTypes:"
                + Utilities.makeMultiCodeBlock("\"+\" or \"add\" or \"plus\""
                + "\n\"-\" or \"sub\" or \"minus\""
                + "\n\"*\" or \"times\" or \"multiply\""
                + "\n\"/\" or \"divide\""
                + "\n\"^\""
                + "\n\"%\" or \"modulus\""
                + "\n\"√\" or \"root\""
                + "\n\"+e\""
                + "\n\"-e\""
                + "\n\"*e\""
                + "\n\"max\""
                + "\n\"min\""
        );
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}
