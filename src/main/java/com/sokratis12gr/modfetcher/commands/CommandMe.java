package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandMe implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.withDescription(message.getAuthor().getName() + "\n"
                + "Account ID: " + message.getAuthor().getID() + "\n"
                + "Account Created: " + message.getAuthor().getCreationDate() + "\n"
                + "Account Avatar URL: " + "[Here](" + message.getAuthor().getAvatarURL() + ")" + "\n"
                + "Account Status: " + message.getAuthor().getStatus().getType().name() + "\n"
        );
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withFooterText("ModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        embed.withColor(Color.CYAN);
        sendMessage(message.getChannel(), "", embed.build());
    }

    @Override
    public String getDescription() {
        return "Provides information about the sender of the command";
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }
}
