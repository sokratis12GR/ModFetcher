package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getBuild;
import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandMe extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] params) {
        getEmbed().withDescription(message.getAuthor().getName() + "\n"
                + "Account ID: " + message.getAuthor().getID() + "\n"
                + "Account Created: " + message.getAuthor().getCreationDate() + "\n"
                + "Account Avatar URL: " + "[Here](" + message.getAuthor().getAvatarURL() + ")" + "\n"
                + "Account Status: " + message.getAuthor().getStatus().getType().name() + "\n"
                + "Account Presence: " + message.getAuthor().getPresence().name()
        );
        sendMessage(message.getChannel(), "", getBuild());
    }

    @Override
    public String getDescription() {
        return "Provides information about the sender of the command";
    }
}
