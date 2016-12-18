package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.CommandHandler;
import com.sokratis12gr.modfetcher.ModFetcher;
import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Map.Entry;

import static com.sokratis12gr.modfetcher.util.Utilities.sendPrivateMessage;

public class CommandHelp implements Command {

    @Override
    public void processCommand(IMessage message, String[] args) {
        final EmbedBuilder embed = new EmbedBuilder();
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

        embed.withDesc(descriptions);
        embed.withColor(Color.YELLOW);
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withTitle("Commands for ModFetcher");
        embed.withFooterText("\nModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        sendPrivateMessage(message.getAuthor(), "", embed.build());
    }

    @Override
    public String getDescription() {
        return "Lists all commands available to the user, along with a basic description of each command. You can run the command with other command names as additional arguments to get a more thorough description of the command.";
    }

    @Override
    public String getThoroughDescription() {
        return "Provides a list of all commands that the user can use, and their descriptions. The names of other commands can be added on to the end of the command to get a thorough explanation of those commands. Example: !help barcode";
    }
}