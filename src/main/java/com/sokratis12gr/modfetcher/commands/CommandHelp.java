package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.CommandHandler;
import com.sokratis12gr.modfetcher.ModFetcher;
import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Map.Entry;

public class CommandHelp implements Command {
    
    @Override
    public void processCommand(IMessage message, String[] args) {
        String descriptions = "";
        
        if (args.length > 1)
            for (int index = 1; index < args.length; index++) {
                
                final Command cmd = CommandHandler.getCommand(args[index]);
                
                if (cmd != null && cmd.isValidUsage(message))
                    descriptions += ModFetcher.COMMAND_KEY + args[index] + " - " + cmd.getThoroughDescription() + Utilities.SEPERATOR + Utilities.SEPERATOR;
            }
        else
            for (final Entry<String, Command> command : CommandHandler.getCommands().entrySet())
                if (command.getValue().isValidUsage(message))
                    descriptions += ModFetcher.COMMAND_KEY + command.getKey() + " - " + command.getValue().getDescription() + Utilities.SEPERATOR + Utilities.SEPERATOR;
                
        Utilities.sendPrivateMessage(message.getAuthor(), Utilities.makeMultiCodeBlock(descriptions));
    }
    
    @Override
    public String getDescription () {
        return "Lists all commands available to the user, along with a basic description of each command. You can run the command with other command names as additional arguments to get a more thorough description of the command.";
    }
    
    @Override
    public String getThoroughDescription () {
        return "Provides a list of all commands that the user can use, and their descriptions. The names of other commands can be added on to the end of the command to get a thorough explanation of those commands. Example: !help barcode";
    }
}