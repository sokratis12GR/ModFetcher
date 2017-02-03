package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.makeErrorMessage;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.Math.*;

public class CommandCalculate extends CommandUser {

    private String[] calcTypes = new String[5];
    private double[] numbers = new double[6];
    private Number calculated;
    private Number[] calculations = new Number[6];

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        final String messageError = makeErrorMessage("Please use: /calc <number_one> <type> <number_two>...");
        double[] numbersIn = numbers;

        if (args.length > 1) {
            numbersIn[0] = !Pattern.matches(".*[a-zA-Z].*", args[1]) ? Double.valueOf(args[1]) : 0;
            description = "Result: " + getCalculations();
            if (args.length > 2) {
                calcTypes[0] = args[2];
                description = "Result: " + getCalculations();
                if (args.length > 3) {
                    numbersIn[1] = !Pattern.matches(".*[a-zA-Z].*", args[3]) ? Double.valueOf(args[3]) : 0;
                    doCalculations(false, false, false, false, false);
                    description = "Result: " + getCalculations();
                    if (args.length > 4) {
                        calcTypes[1] = args[4];
                        description = "Result: " + getCalculations();
                        if (args.length > 5) {
                            numbersIn[2] = !Pattern.matches(".*[a-zA-Z].*", args[5]) ? Double.valueOf(args[5]) : 0;
                            doCalculations(true, false, false, false, false);
                            description = "Result: " + getCalculations();
                            if (args.length > 6) {
                                calcTypes[2] = args[6];
                                description = "Result: " + getCalculations();
                                if (args.length > 7) {
                                    numbersIn[3] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[7]) : 0;
                                    doCalculations(true, true, false, false, false);
                                    description = "Result: " + getCalculations();
                                    if (args.length > 8) {
                                        calcTypes[3] = args[8];
                                        description = "Result: " + getCalculations();
                                        if (args.length > 9) {
                                            numbersIn[4] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[9]) : 0;
                                            doCalculations(true, true, true, false, false);
                                            description = "Result: " + getCalculations();
                                            if (args.length > 10) {
                                                calcTypes[4] = args[10];
                                                description = "Result: " + getCalculations();
                                                if (args.length > 11) {
                                                    numbersIn[5] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[11]) : 0;
                                                    doCalculations(true, true, true, true, false);
                                                    description = "Result: " + getCalculations();
                                                    if (args.length > 12) {
                                                        calcTypes[5] = args[12];
                                                        description = "Result: " + getCalculations();
                                                        if (args.length > 13) {
                                                            numbersIn[6] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[13]) : 0;
                                                            doCalculations(true, true, true, true, true);
                                                            description = "Result: " + getCalculations();
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
        return calculated != null ? calculated : 0;
    }

    private void doCalculations(boolean secondCalc, boolean thirdCalc, boolean fourthCalc, boolean fifthCalc, boolean sixthCalc) {
        Number result;
        calculations[0] = calcSwitch(calcTypes[0], numbers[0], numbers[1]);
        result = calculations[0];
        if (secondCalc) {
            calculations[1] = calcSwitch(calcTypes[1], calculations[0], numbers[2]);
            result = calculations[1];
            if (thirdCalc) {
                calculations[2] = calcSwitch(calcTypes[2], calculations[1], numbers[3]);
                result = calculations[2];
                if (fourthCalc) {
                    calculations[3] = calcSwitch(calcTypes[3], calculations[2], numbers[4]);
                    result = calculations[3];
                    if (fifthCalc) {
                        calculations[4] = calcSwitch(calcTypes[4], calculations[3], numbers[5]);
                        result = calculations[4];
                        if (sixthCalc) {
                            calculations[5] = calcSwitch(calcTypes[5], calculations[4], numbers[6]);
                            result = calculations[5];
                        }
                    }
                }
            }
        }
        calculated = result;
    }

    private static Number calcSwitch(String calc, Number one, double two) {
        Number result = 0;
        Number calculated;
        switch (calc) {
            case "+":
            case "add":
            case "plus":
                calculated = (double) one + two;
                result = calculated;
                break;
            case "-":
            case "minus":
            case "sub":
                calculated = (double) one - two;
                result = calculated;
                break;
            case "*":
            case "multiply":
            case "times":
                calculated = (double) one * two;
                result = calculated;
                break;
            case "/":
            case "divide":
                calculated = (double) one / two;
                result = calculated;
                break;
            case "^":
                calculated = pow((double) one, two);
                result = calculated;
                break;
            case "%":
            case "modulus":
                calculated = (double) one % two;
                result = calculated;
                break;
            case "√":
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
                + Utilities.makeMultilineDiffMessage(false,
                "\"+\" or \"add\" or \"plus\"",
                "\"-\" or \"sub\" or \"minus\"",
                "\"*\" or \"times\" or \"multiply\"",
                "\"/\" or \"divide\"",
                "\"^\"",
                "\"%\" or \"modulus\"",
                "\"√\" or \"root\"",
                "\"+e\"",
                "\"-e\"",
                "\"*e\"",
                "\"max\"",
                "\"min\"",
                "\"floorMod\"",
                "\"floorDiv\""
        );
    }

}
