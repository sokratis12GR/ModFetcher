package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandServer implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        if (message.getGuild() != null)
            getEmbed().withDescription("Guild Name: " + message.getGuild().getName() + "\n"
                    + "Guild ID: " + message.getGuild().getID() + "\n"
                    + "Channels in " + message.getGuild().getName() + ": " + message.getGuild().getChannels().size() + "\n"
                    + "Users in " + message.getGuild().getName() + ": " + message.getGuild().getUsers().size() + "\n"
                    + "Roles in " + message.getGuild().getName() + ": " + message.getGuild().getRoles().size() + "\n"
            );
        sendMessage(message.getChannel(), "", getEmbed().build());
    }

    @Override
    public String getDescription() {
        return "Provides information about the server that the command was executed at";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }
}
