package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandCalculate implements Command {

    private char calcTypeOne;
    private long[] numbers = new long[]{0, 0};

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description;

        long[] numbersIn = numbers;

        if (args.length > 1)
            if (!Pattern.matches(".*[a-zA-Z].*", args[1])) {
                numbersIn[0] = Long.valueOf(args[1]);
                if (args.length > 2) {
                    calcTypeOne = args[2].charAt(0);
                    if (args.length > 3) {
                        numbersIn[1] = Long.valueOf(args[3]);
                        description = "Result: " + doCalculations();
                        sendMessage(message.getChannel(), description);
                    } else {
                        description = "Please use ***$calc <number_one> <type> <number_two>...***";
                        sendMessage(message.getChannel(), description);
                    }
                } else {
                    description = "Result: " + numbersIn[0];
                    sendMessage(message.getChannel(), description);

                }
            } else {
                description = "Please use ***$calc <number_one> <type> <number_two>...***";
                sendMessage(message.getChannel(), description);
            }
    }

    private long doCalculations() {
        switch (calcTypeOne) {
            case '+':
                long resultAdd = numbers[0] + numbers[1];
                return resultAdd <= Long.MIN_VALUE || resultAdd >= Long.MAX_VALUE ? 0 : resultAdd;
            case '-':
                long resultSub = numbers[0] - numbers[1];
                return resultSub <= Long.MIN_VALUE || resultSub >= Long.MAX_VALUE ? 0 : resultSub;
            case '*':
                long resultMul = numbers[0] * numbers[1];
                return resultMul <= Long.MIN_VALUE || resultMul >= Long.MAX_VALUE ? 0 : resultMul;
            case '/':
                long resultDiv = numbers[0] / numbers[1];
                return resultDiv <= Long.MIN_VALUE || resultDiv >= Long.MAX_VALUE ? 0 : resultDiv;
        }
        return 0;
    }

    @Override
    public String getDescription() {
        return "Does some basic calculations. \nTypes: \"\'+\' , \'-\', \'*\', \'/\' \"";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}
