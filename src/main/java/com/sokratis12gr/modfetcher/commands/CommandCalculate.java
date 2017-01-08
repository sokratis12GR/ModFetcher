package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.Math.*;

public class CommandCalculate extends CommandUser {

    private String calcTypeOne = "";
    private String calcTypeTwo = "";
    private String calcTypeThree = "";
    private String calcTypeFour = "";
    private String calcTypeFive = "";
    private String calcTypeSix = "";
    private double[] numbers = new double[]{0, 0, 0, 0, 0, 0, 0};
    private Number calculated;
    private boolean calc1 = false;
    private boolean calc2 = false;
    private boolean calc3 = false;
    private boolean calc4 = false;
    private boolean calc5 = false;
    private boolean calc6 = false;
    private Number numberOne = 0;
    private Number numberTwo = 0;
    private Number numberThree = 0;
    private Number numberFour = 0;
    private Number numberFive = 0;
    private Number numberSix = 0;

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        final String messageError = "Please use ***$calc <number_one> <type> <number_two>...***";
        double[] numbersIn = numbers;

        if (args.length > 1) {
            numbersIn[0] = !Pattern.matches(".*[^0-9].*", args[1]) ? Double.valueOf(args[1]) : 0;
            description = "Result: " + getCalculations();
            if (args.length > 2) {
                calcTypeOne = args[2];
                description = "Result: " + getCalculations();
                if (args.length > 3) {
                    numbersIn[1] = !Pattern.matches(".*[^0-9].*", args[3]) ? Double.valueOf(args[3]) : 0;
                    doCalculations(false, false, false, false, false);
                    description = "Result: " + getCalculations();
                    if (args.length > 4) {
                        calcTypeTwo = args[4];
                        description = "Result: " + getCalculations();
                        if (args.length > 5) {
                            numbersIn[2] = !Pattern.matches(".*[^0-9].*", args[5]) ? Double.valueOf(args[5]) : 0;
                            doCalculations(true, false, false, false, false);
                            description = "Result: " + getCalculations();
                            if (args.length > 6) {
                                calcTypeThree = args[6];
                                description = "Result: " + getCalculations();
                                if (args.length > 7) {
                                    numbersIn[3] = !Pattern.matches(".*[^0-9].*", args[7]) ? Double.valueOf(args[7]) : 0;
                                    doCalculations(true, true, false, false, false);
                                    description = "Result: " + getCalculations();
                                    if (args.length > 8) {
                                        calcTypeFour = args[8];
                                        description = "Result: " + getCalculations();
                                        if (args.length > 9) {
                                            numbersIn[4] = !Objects.equals(args[9], ".*[^0-9].*") ? Double.valueOf(args[9]) : 0;
                                            doCalculations(true, true, true, false, false);
                                            description = "Result: " + getCalculations();
                                            if (args.length > 10) {
                                                calcTypeFive = args[10];
                                                description = "Result: " + getCalculations();
                                                if (args.length > 11) {
                                                    numbersIn[5] = !Objects.equals(args[11], ".*[^0-9].*") ? Double.valueOf(args[11]) : 0;
                                                    doCalculations(true, true, true, true, false);
                                                    description = "Result: " + getCalculations();
                                                    if (args.length > 12) {
                                                        calcTypeSix = args[12];
                                                        description = "Result: " + getCalculations();
                                                        if (args.length > 13) {
                                                            numbersIn[6] = !Objects.equals(args[13], ".*[^0-9].*") ? Double.valueOf(args[13]) : 0;
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
        if (calc1) {
            return numberOne;
        } else if (calc2) {
            return numberTwo;
        } else if (calc3) {
            return numberThree;
        } else if (calc4) {
            return numberFour;
        } else if (calc5) {
            return numberFive;
        } else if (calc6) {
            return numberSix;
        }
        return 0;
    }

    private void doCalculations(boolean secondCalc, boolean thirdCalc, boolean fourthCalc, boolean fifthCalc, boolean sixthCalc) {
        Number result = 0;

        double first = numbers[0];
        double second = numbers[1];
        double third = numbers[2];
        double four = numbers[3];
        double five = numbers[4];
        double six = numbers[5];
        double seven = numbers[6];
        numberOne = calcSwitch(calcTypeOne, first, second);
        result = numberOne;
        calc1 = true;
        if (secondCalc) {
            numberTwo = calcSwitch(calcTypeTwo, numberOne, third);
            result = numberTwo;
            calc2 = true;
            if (thirdCalc) {
                numberThree = calcSwitch(calcTypeThree, numberTwo, four);
                result = numberThree;
                calc3 = true;
                if (fourthCalc) {
                    numberFour = calcSwitch(calcTypeFour, numberThree, five);
                    result = numberFour;
                    calc4 = true;
                    if (fifthCalc) {
                        numberFive = calcSwitch(calcTypeFive, numberFour, six);
                        result = numberFive;
                        calc5 = true;
                        if (sixthCalc) {
                            numberSix = calcSwitch(calcTypeSix, numberFive, seven);
                            result = numberSix;
                            calc6 = true;
                        }
                    }
                }
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
