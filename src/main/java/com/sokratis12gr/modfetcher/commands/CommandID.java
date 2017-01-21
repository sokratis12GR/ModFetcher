package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandID extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] args) {
        final IUser user = message.getAuthor();
        sendMessage(message.getChannel(), "Your ID is: \\" + user);
    }

    @Override
    public String getDescription() {
        return "Returns the ID of the user";
    }
}
