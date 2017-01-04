package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.String.format;

public class CommandMemberCount extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final int memberCount = message.getChannel().getUsersHere().size();
        sendMessage(message.getChannel(), format("There are %d people in this channel :)", memberCount));
    }

    @Override
    public String getDescription() {
        return "Counts the amount of members in the room.";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }
}
