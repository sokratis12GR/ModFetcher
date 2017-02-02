package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.makeErrorMessage;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandCheck extends CommandUser {

    private String[] checkTypes = new String[5];
    private double[] numbers = new double[6];
    private boolean checkResult;
    private boolean[] checks = new boolean[5];

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        final String messageError = makeErrorMessage("Please use: /check <number_one> <type> <number_two>...");
        double[] numbersIn = numbers;

        if (args.length > 1) {
            numbersIn[0] = !Pattern.matches(".*[a-zA-Z].*", args[1]) ? Double.valueOf(args[1]) : 0;
            description = "Result: " + getCheck();
            if (args.length > 2) {
                checkTypes[0] = args[2];
                description = "Result: " + getCheck();
                if (args.length > 3) {
                    numbersIn[1] = !Pattern.matches(".*[a-zA-Z].*", args[3]) ? Double.valueOf(args[3]) : 0;
                    doChecks(false, false, false, false, false);
                    description = "Result: " + getCheck();
                    if (args.length > 4) {
                        checkTypes[1] = args[4];
                        description = "Result: " + getCheck();
                        if (args.length > 5) {
                            numbersIn[2] = !Pattern.matches(".*[a-zA-Z].*", args[5]) ? Double.valueOf(args[5]) : 0;
                            doChecks(true, false, false, false, false);
                            description = "Result: " + getCheck();
                            if (args.length > 6) {
                                checkTypes[2] = args[6];
                                description = "Result: " + getCheck();
                                if (args.length > 7) {
                                    numbersIn[3] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[7]) : 0;
                                    doChecks(true, true, false, false, false);
                                    description = "Result: " + getCheck();
                                    if (args.length > 8) {
                                        checkTypes[3] = args[8];
                                        description = "Result: " + getCheck();
                                        if (args.length > 9) {
                                            numbersIn[4] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[9]) : 0;
                                            doChecks(true, true, true, false, false);
                                            description = "Result: " + getCheck();
                                            if (args.length > 10) {
                                                checkTypes[4] = args[10];
                                                description = "Result: " + getCheck();
                                                if (args.length > 11) {
                                                    numbersIn[5] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[11]) : 0;
                                                    doChecks(true, true, true, true, false);
                                                    description = "Result: " + getCheck();
                                                    if (args.length > 12) {
                                                        checkTypes[5] = args[12];
                                                        description = "Result: " + getCheck();
                                                        if (args.length > 13) {
                                                            numbersIn[6] = !Pattern.matches(".*[a-zA-Z].*", args[7]) ? Double.valueOf(args[13]) : 0;
                                                            doChecks(true, true, true, true, true);
                                                            description = "Result: " + getCheck();
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

    private boolean getCheck() {
        return checkResult;
    }

    private void doChecks(boolean secondCheck, boolean thirdCheck, boolean fourthCheck, boolean fifthCheck, boolean sixthCheck) {
        boolean result;
        checks[0] = checkSwitch(checkTypes[0], numbers[0], numbers[1]);
        result = checks[0];
        if (secondCheck) {
            checks[1] = checkSwitch(checkTypes[1], numbers[1], numbers[2]);
            result = checks[1];
            if (thirdCheck) {
                checks[2] = checkSwitch(checkTypes[2], numbers[2], numbers[3]);
                result = checks[2];
                if (fourthCheck) {
                    checks[3] = checkSwitch(checkTypes[3], numbers[3], numbers[4]);
                    result = checks[3];
                    if (fifthCheck) {
                        checks[4] = checkSwitch(checkTypes[4], numbers[4], numbers[5]);
                        result = checks[4];
                        if (sixthCheck) {
                            checks[5] = checkSwitch(checkTypes[5], numbers[5], numbers[6]);
                            result = checks[5];
                        }
                    }
                }
            }
        }
        checkResult = result;
    }

    private static boolean checkSwitch(String check, double one, double two) {
        boolean result = false;
        boolean checked;
        switch (check) {
            case "greater":
            case "better":
            case "more":
            case ">":
                checked = one > two;
                result = checked;
                break;
            case "worse":
            case "less":
            case "<":
                checked = one < two;
                result = checked;
                break;
            case "equals":
            case "equal":
            case "same":
            case "==":
            case "=":
                checked = one == two;
                result = checked;
                break;
            case ">=":
            case "=>":
                checked = one >= two;
                result = checked;
                break;
            case "<=":
            case "=<":
                checked = one <= two;
                result = checked;
                break;
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "Does checks.\nTypes:"
                + Utilities.makeMultilineDiffMessage(false,
                "\">\" or \"better\" or \"more\" or \"greater\"",
                "\"<\" or \"worse\" or \"less\"",
                "\"=\" or \"==\" or \"same\" or \"equal\" or \"equals\"",
                "\">=\" or \"=>\"",
                "\"<=\" or \"=<\""
        );
    }

}
