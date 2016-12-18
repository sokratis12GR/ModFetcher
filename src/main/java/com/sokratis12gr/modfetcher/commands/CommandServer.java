package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandServer implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.withFooterText("\nModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        if (message.getGuild() != null)
            embed.withDescription("Guild Name: " + message.getGuild().getName() + "\n"
                    + "Guild ID: " + message.getGuild().getID() + "\n"
                    + "Channels in " + message.getGuild().getName() + ": " + message.getGuild().getChannels().size() + "\n"
                    + "Users in " + message.getGuild().getName() + ": " + message.getGuild().getUsers().size() + "\n"
                    + "Roles in " + message.getGuild().getName() + ": " + message.getGuild().getRoles().size() + "\n"
            );
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withColor(Color.CYAN);
        sendMessage(message.getChannel(), "", embed.build());
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
