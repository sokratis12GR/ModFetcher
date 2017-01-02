package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandCalculate implements Command {

    private char calcTypeOne;
    private char calcTypeTwo;
    private double[] numbers = new double[]{0, 0, 0};

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
                        description = "Result: " + doCalculations(false);
                        if (args.length > 4) {
                            calcTypeTwo = args[4].charAt(0);
                            if (args.length > 5) {
                                numbersIn[2] = Double.valueOf(args[5]);
                                description = "Result: " + doCalculations(true);
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

    private double doCalculations(boolean secondCalc) {
        double result = 0;
        double numberOne = 0;
        switch (calcTypeOne) {
            case '+':
                numberOne = numbers[0] + numbers[1];
                result = numberOne;
                break;
            case '-':
                numberOne = numbers[0] - numbers[1];
                result = numberOne;
                break;
            case '*':
                numberOne = numbers[0] * numbers[1];
                result = numberOne;

                break;
            case '/':
                numberOne = numbers[0] / numbers[1];
                result = numberOne;

                break;
            case '^':
                numberOne = (long) numbers[0] ^ (long) numbers[1];
                result = numberOne;
                break;

            case '%':
                numberOne = numbers[0] % numbers[1];
                result = numberOne;
                break;
        }
        if (secondCalc) {
            double numberTwo = 0;
            switch (calcTypeTwo) {
                case '+':
                    numberTwo = numberOne + numbers[2];
                    result = numberTwo;
                    break;
                case '-':
                    numberTwo = numberOne - numbers[2];
                    result = numberTwo;
                    break;
                case '*':
                    numberTwo = numberOne * numbers[2];
                    result = numberTwo;
                    break;
                case '/':
                    numberTwo = numberOne / numbers[2];
                    result = numberTwo;
                    break;
                case '^':
                    numberTwo = (long) numberOne ^ (long) numbers[2];
                    result = numberTwo;
                    break;
                case '%':
                    numberTwo = numberOne % numbers[2];
                    result = numberTwo;
                    break;
            }
        }
        return result <= Double.MIN_VALUE || result >= Double.MAX_VALUE ? 0 : result;
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
