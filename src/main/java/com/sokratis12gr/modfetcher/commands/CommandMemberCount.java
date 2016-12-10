package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public class CommandMemberCount implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final int memberCount = message.getChannel().getUsersHere().size();

        Utilities.sendMessage(message.getChannel(), String.format("There are %d people in this channel :)", memberCount));
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
