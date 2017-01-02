package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.Math.*;

public class CommandCalculate implements Command {

    private String calcTypeOne;
    private String calcTypeTwo;
    private String calcTypeThree;
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
                    if (args.length > 3) {
                        numbersIn[1] = Double.valueOf(args[3]);
                        doCalculations(false, false);
                        description = "Result: " + getCalculations();
                        if (args.length > 4) {
                            calcTypeTwo = args[4];
                            if (args.length > 5) {
                                numbersIn[2] = Double.valueOf(args[5]);
                                doCalculations(true, false);
                                description = "Result: " + getCalculations();
                                if (args.length > 6) {
                                    calcTypeThree = args[6];
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
        switch (calcTypeOne) {
            case "+":
                numberOne = first + second;
                result = numberOne;
                break;
            case "add":
                numberOne = first + second;
                result = numberOne;
                break;
            case "plus":
                numberOne = first + second;
                result = numberOne;
                break;
            case "-":
                numberOne = first - second;
                result = numberOne;
                break;
            case "minus":
                numberOne = first - second;
                result = numberOne;
                break;
            case "sub":
                numberOne = first - second;
                result = numberOne;
                break;
            case "*":
                numberOne = first * second;
                result = numberOne;
                break;
            case "multiply":
                numberOne = first * second;
                result = numberOne;
                break;
            case "times":
                numberOne = first * second;
                result = numberOne;
                break;
            case "/":
                numberOne = first / second;
                result = numberOne;
                break;
            case "divide":
                numberOne = first / second;
                result = numberOne;
                break;
            case "^":
                numberOne = pow(first, second);
                result = numberOne;
                break;
            case "%":
                numberOne = first % second;
                result = numberOne;
                break;
            case "modulus":
                numberOne = first % second;
                result = numberOne;
                break;
            case "√":
                numberOne = pow(first, 1 / second);
                result = numberOne;
                break;
            case "root":
                numberOne = pow(first, 1 / second);
                result = numberOne;
                break;
            case "+e":
                numberOne = addExact((long) first, (long) second);
                result = numberOne;
                break;
            case "-e":
                numberOne = subtractExact((long) first, (long) second);
                result = numberOne;
                break;
            case "*e":
                numberOne = multiplyExact((long) first, (long) second);
                result = numberOne;
                break;
            case "max":
                numberOne = max(first, second);
                result = numberOne;
                break;
            case "min":
                numberOne = min(first, second);
                result = numberOne;
                break;
        }
        if (secondCalc) {
            Number numberTwo = 0;
            double third = numbers[2];
            switch (calcTypeTwo) {
                case "+":
                    numberTwo = (double) numberOne + third;
                    result = numberTwo;
                    break;
                case "add":
                    numberTwo = (double) numberOne + third;
                    result = numberTwo;
                    break;
                case "plus":
                    numberTwo = (double) numberOne + third;
                    result = numberTwo;
                    break;
                case "-":
                    numberTwo = (double) numberOne - third;
                    result = numberTwo;
                    break;
                case "minus":
                    numberTwo = (double) numberOne - third;
                    result = numberTwo;
                    break;
                case "sub":
                    numberTwo = (double) numberOne - third;
                    result = numberTwo;
                    break;
                case "*":
                    numberTwo = (double) numberOne * third;
                    result = numberTwo;
                    break;
                case "multiply":
                    numberTwo = (double) numberOne * third;
                    result = numberTwo;
                    break;
                case "times":
                    numberTwo = (double) numberOne * third;
                    result = numberTwo;
                    break;
                case "/":
                    numberTwo = (double) numberOne / third;
                    result = numberTwo;
                    break;
                case "divide":
                    numberTwo = (double) numberOne / third;
                    result = numberTwo;
                    break;
                case "^":
                    numberTwo = pow((double) numberOne, third);
                    result = numberTwo;
                    break;
                case "%":
                    numberTwo = (double) numberOne % third;
                    result = numberTwo;
                    break;
                case "modulus":
                    numberTwo = (double) numberOne % third;
                    result = numberTwo;
                    break;
                case "√":
                    numberTwo = pow((double) numberOne, 1 / third);
                    result = numberTwo;
                    break;
                case "root":
                    numberTwo = pow((double) numberOne, 1 / third);
                    result = numberTwo;
                    break;
                case "+e":
                    numberTwo = addExact((long) numberOne, (long) third);
                    result = numberTwo;
                    break;
                case "-e":
                    numberTwo = subtractExact((long) numberOne, (long) third);
                    result = numberTwo;
                    break;
                case "*e":
                    numberTwo = multiplyExact((long) numberOne, (long) third);
                    result = numberTwo;
                    break;
                case "max":
                    numberTwo = max((double) numberOne, third);
                    result = numberTwo;
                    break;
                case "min":
                    numberTwo = min((double) numberOne, third);
                    result = numberTwo;
                    break;
            }
            if (thirdCalc) {
                Number numberThree = 0;
                double four = numbers[3];
                switch (calcTypeThree) {
                    case "+":
                        numberThree = (double) numberTwo + four;
                        result = numberThree;
                        break;
                    case "add":
                        numberThree = (double) numberTwo + four;
                        result = numberThree;
                        break;
                    case "plus":
                        numberThree = (double) numberTwo + four;
                        result = numberThree;
                        break;
                    case "-":
                        numberThree = (double) numberTwo - four;
                        result = numberThree;
                        break;
                    case "minus":
                        numberThree = (double) numberTwo - four;
                        result = numberThree;
                        break;
                    case "sub":
                        numberThree = (double) numberTwo - four;
                        result = numberThree;
                        break;
                    case "*":
                        numberThree = (double) numberTwo * four;
                        result = numberThree;
                        break;
                    case "multiply":
                        numberThree = (double) numberTwo * four;
                        result = numberThree;
                        break;
                    case "times":
                        numberThree = (double) numberTwo * four;
                        result = numberThree;
                        break;
                    case "/":
                        numberThree = (double) numberTwo / four;
                        result = numberThree;
                        break;
                    case "divide":
                        numberThree = (double) numberTwo / four;
                        result = numberThree;
                        break;
                    case "^":
                        numberThree = pow((double) numberTwo, four);
                        result = numberThree;
                        break;
                    case "%":
                        numberThree = (double) numberTwo % four;
                        result = numberThree;
                        break;
                    case "modulus":
                        numberThree = (double) numberTwo % four;
                        result = numberThree;
                        break;
                    case "√":
                        numberThree = pow((double) numberTwo, 1 / four);
                        result = numberThree;
                        break;
                    case "root":
                        numberThree = pow((double) numberTwo, 1 / four);
                        result = numberThree;
                        break;
                    case "+e":
                        numberThree = addExact((long) numberTwo, (long) four);
                        result = numberThree;
                        break;
                    case "-e":
                        numberThree = subtractExact((long) numberTwo, (long) four);
                        result = numberThree;
                        break;
                    case "*e":
                        numberThree = multiplyExact((long) numberTwo, (long) four);
                        result = numberThree;
                        break;
                    case "max":
                        numberThree = max((double) numberTwo, four);
                        result = numberThree;
                        break;
                    case "min":
                        numberThree = min((double) numberTwo, four);
                        result = numberThree;
                        break;
                }
            }
        }
        calculated = result;
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
