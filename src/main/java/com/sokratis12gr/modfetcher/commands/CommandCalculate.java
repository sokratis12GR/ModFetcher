package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandCalculate implements Command {

    private char calcTypeOne;
    private char calcTypeTwo;
    private double[] numbers = new double[]{0, 0, 0};
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
                    calcTypeOne = args[2].charAt(0);
                    if (args.length > 3) {
                        numbersIn[1] = Double.valueOf(args[3]);
                        doCalculations(false);
                        description = "Result: " + getCalculations();
                        if (args.length > 4) {
                            calcTypeTwo = args[4].charAt(0);
                            if (args.length > 5) {
                                numbersIn[2] = Double.valueOf(args[5]);
                                doCalculations(true);
                                description = "Result: " + getCalculations();
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

    private double getCalculations() {
        return (double) calculated;
    }

    private void doCalculations(boolean secondCalc) {
        Number result = 0;
        Number numberOne = 0;
        double first = numbers[0];
        double second = numbers[1];
        switch (calcTypeOne) {
            case '+':
                numberOne = first + second;
                result = numberOne;
                break;
            case '-':
                numberOne = first - second;
                result = numberOne;
                break;
            case '*':
                numberOne = first * second;
                result = numberOne;
                break;
            case '/':
                numberOne = first / second;
                result = numberOne;
                break;
            case '^':
                numberOne = Math.pow(first, second);
                result = numberOne;
                break;
            case '%':
                numberOne = first % second;
                result = numberOne;
                break;
        }
        if (secondCalc) {
            Number numberTwo = 0;
            double third = numbers[2];
            switch (calcTypeTwo) {
                case '+':
                    numberTwo = (double) numberOne + third;
                    result = numberTwo;
                    break;
                case '-':
                    numberTwo = (double) numberOne - third;
                    result = numberTwo;
                    break;
                case '*':
                    numberTwo = (double) numberOne * third;
                    result = numberTwo;
                    break;
                case '/':
                    numberTwo = (double) numberOne / third;
                    result = numberTwo;
                    break;
                case '^':
                    numberTwo = Math.pow((double) numberOne, third);
                    result = numberTwo;
                    break;
                case '%':
                    numberTwo = (double) numberOne % third;
                    result = numberTwo;
                    break;
            }
        }
        calculated = (double) result <= Double.MIN_VALUE || (double) result >= Double.MAX_VALUE ? 0 : (double) result;
    }

    @Override
    public String getDescription() {
        return "Does some basic calculations.Types: \"\'+\' , \'-\', \'*\', \'/\' \"";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}
