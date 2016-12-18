package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.ModFetcher;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandReload implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withFooterText("\nModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        embed.withColor(Color.RED);
        embed.withDesc("Reloading handlers and resource!");
        sendMessage(message.getChannel(), "", embed.build());
        ModFetcher.initHandlers();
        embed.withDesc("Reload complete. That tickled ;)");
        sendMessage(message.getChannel(), "", embed.build());
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
