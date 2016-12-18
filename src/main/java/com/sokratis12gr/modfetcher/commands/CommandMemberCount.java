package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;
import static java.lang.String.format;

public class CommandMemberCount implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final EmbedBuilder embed = new EmbedBuilder();
        final int memberCount = message.getChannel().getUsersHere().size();
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withColor(Color.GREEN);
        embed.withDescription(format("There are %d people in this channel :)", memberCount));
        embed.withFooterText("\nModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        sendMessage(message.getChannel(), "", embed.build());
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
