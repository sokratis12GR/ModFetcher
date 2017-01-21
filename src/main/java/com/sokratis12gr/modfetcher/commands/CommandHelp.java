package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.CommandHandler;
import com.sokratis12gr.modfetcher.ModFetcher;
import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Map.Entry;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendPrivateMessage;

public class CommandHelp extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] args) {
        String descriptions = "";

        if (args.length > 1)
            for (int index = 1; index < args.length; index++) {

                final Command cmd = CommandHandler.getCommand(args[index]);

                if (cmd != null && cmd.isValidUsage(message))
                    descriptions += ModFetcher.COMMAND_KEY + args[index] + " - " + cmd.getDescription() + Utilities.SEPERATOR + Utilities.SEPERATOR;
            }
        else
            for (final Entry<String, Command> command : CommandHandler.getCommands().entrySet())
                if (command.getValue().isValidUsage(message))
                    descriptions += ModFetcher.COMMAND_KEY + command.getKey() + " - " + command.getValue().getDescription() + Utilities.SEPERATOR + Utilities.SEPERATOR;

        getEmbed().withDesc(descriptions);
        sendPrivateMessage(message.getAuthor(), "", getEmbed().build());
    }

    @Override
    public String getDescription() {
        return "Lists all commands available to the user, along with a basic description of each command. You can run the command with other command names as additional arguments to get a more thorough description of the command.";
    }
}