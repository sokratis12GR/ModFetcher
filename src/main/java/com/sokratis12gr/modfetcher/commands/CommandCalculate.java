package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandCalculate implements Command {

    private char calcTypeOne;
    private char calcTypeTwo;
    private long[] numbers = new long[]{0, 0, 0};

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        String messageError = "Please use ***$calc <number_one> <type> <number_two>...***";
        long[] numbersIn = numbers;

        if (args.length > 1) {
            if (!Pattern.matches(".*[a-zA-Z].*", args[1])) {
                numbersIn[0] = Long.valueOf(args[1]);
                if (args.length > 2) {
                    calcTypeOne = args[2].charAt(0);
                    if (args.length > 3) {
                        if (Long.valueOf(args[3]) != null)
                            numbersIn[1] = Long.valueOf(args[3]);
                        description = "Result: " + doCalculations(false);
                        if (args.length > 4) {
                            calcTypeTwo = args[4].charAt(0);
                            if (args.length > 5) {
                                if (Long.valueOf(args[5]) != null)
                                    numbersIn[2] = Long.valueOf(args[5]);
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

    private long doCalculations(boolean secondCalc) {
        long result = 0;
        long numberOne = 0;
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
                numberOne = (numbers[0] ^ numbers[1]);
                result = numberOne;
                break;

            case '%':
                numberOne = (numbers[0] % numbers[1]);
                result = numberOne;
                break;
        }
        if (secondCalc) {
            long numberTwo = 0;
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
                    numberTwo = numberOne ^ numbers[2];
                    result = numberTwo;
                    break;
                case '%':
                    numberTwo = numberOne % numbers[2];
                    result = numberTwo;
                    break;
            }
        }
        return result <= Long.MIN_VALUE || result >= Long.MAX_VALUE ? 0 : result;
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
