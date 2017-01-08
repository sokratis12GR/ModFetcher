package com.sokratis12gr.modfetcher.commands;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.Math.addExact;
import static java.lang.Math.floorDiv;
import static java.lang.Math.floorMod;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.pow;
import static java.lang.Math.subtractExact;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;
import sx.blah.discord.handle.obj.IMessage;
import com.sokratis12gr.modfetcher.util.Utilities;

public class CommandCalculate extends CommandUser {

    private Number calculated;

    @Override
    public void processCommand(IMessage message, String[] args) {
        String description = "";
        final String messageError = "Please use ***$calc <number_one> <type> <number_two>...***";
        Queue<Object> postfix = new LinkedList<>();
        Stack<String> stack = new Stack<>();	

        if (args.length % 2 != 1)
        	sendMessage(message.getChannel(), messageError);
        else
        {
        	for (int i = 1; i < args.length; i += 2)
        	{
        		if (!Pattern.matches(".*[a-zA-Z].*", args[i]))
        			postfix.add(Double.valueOf(args[i]));
        		else
        		{
        			sendMessage(message.getChannel(), messageError);
        			return;
        		}
        		
        		int precedence = getPrecedence(args[i+1]);
        		if (precedence < 0)
        		{
        			sendMessage(message.getChannel(), messageError);
        			return;
        		}
        		else
        		{
        			if (stack.isEmpty() || precedence >= getPrecedence(stack.peek()))
        				stack.push(args[i+1]);
        			else
        			{
        				while (precedence < getPrecedence(stack.peek()))
        					postfix.add(stack.pop());
        				stack.push(args[i+1]);
        			}
        		}
        	}
        	while (!stack.isEmpty())
        		postfix.add(stack.pop());
        	
        	doCalculations(postfix);
        	description = "Result: " + getCalculations();
        }
        
        sendMessage(message.getChannel(), description);
    }
    
    private Number getCalculations() {
        return calculated != null ? calculated : 0;
    }

    private void doCalculations(Queue<?> postfix) {
        Stack<Number> stack = new Stack<>();

        while (!postfix.isEmpty())
        {
        	Object obj = postfix.remove();
        	if (obj instanceof Number)
        		stack.add((Number) obj);
        	else if (obj instanceof String)
        	{
        		Number second = stack.pop();
        		Number first = stack.pop();
        		stack.push(calcSwitch((String) obj, first, second));
        	}
        }
        
        calculated = stack.pop();
    }

    // + - * / ^ % sqrt max min floorMod floorDiv
    
    /*
     * ^
     * * / % floorMod floorDiv
     * + -
     */
    
    private static int getPrecedence(String calc)
    {
    	switch (calc)
    	{
    		case "+":
            case "add":
            case "plus":
            case "-":
            case "minus":
            case "sub":
            case "+e":
            case "-e":
            	return 0;
            case "*":
            case "multiply":
            case "times":
            case "/":
            case "divide":
            case "%":
            case "modulus":
            case "*e":
            case "floorMod":
            case "floorDiv":
                return 1;
            case "^":
            case "√":
            case "root":
                return 2;
            case "max":
            case "min":
                return 3;   
    	}
    	return -1;
    }
    
    private static Number calcSwitch(String calc, Number one, Number two) {
        Number result = 0;
        Number calculated = 0;
        switch (calc) {
            case "+":
            case "add":
            case "plus":
                calculated = (double) one + (double) two;
                result = calculated;
                break;
            case "-":
            case "minus":
            case "sub":
                calculated = (double) one - (double) two;
                result = calculated;
                break;
            case "*":
            case "multiply":
            case "times":
                calculated = (double) one * (double) two;
                result = calculated;
                break;
            case "/":
            case "divide":
                calculated = (double) one / (double) two;
                result = calculated;
                break;
            case "^":
                calculated = pow((double) one, (double) two);
                result = calculated;
                break;
            case "%":
            case "modulus":
                calculated = (double) one % (double) two;
                result = calculated;
                break;
            case "√":
            case "root":
                calculated = pow((double) one, 1 / (double) two);
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
                calculated = max((double) one, (double) two);
                result = calculated;
                break;
            case "min":
                calculated = min((double) one, (double) two);
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
                + "\n\"floorMod\""
                + "\n\"floorDiv\""
        );
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}