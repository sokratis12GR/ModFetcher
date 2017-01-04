package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandID extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] args) {
        final IUser user = message.getAuthor();
        getEmbed().withDesc("\\" + user);
        sendMessage(message.getChannel(), "", getEmbed().build());

    }

    @Override
    public String getDescription() {
        return "Returns the ID of the user";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }
}
