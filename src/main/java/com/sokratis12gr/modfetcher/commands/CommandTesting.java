package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

public class CommandTesting implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.withAuthorUrl("https://sokratis12gr.com");
        embed.withDescription("About sokratis12GR");
        Utilities.sendMessage(message.getChannel(), "", embed.build());
    }

    @Override
    public String getDescription() {
        return "Reloads all of the handlers and resources for the bot, including commands!";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }
}
