package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.ModFetcher;
import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendMessage;

public class CommandReload extends CommandAdmin {

    @Override
    public void processCommand(IMessage message, String[] params) {
        getEmbed().withDesc("Reloading handlers and resource!");
        sendMessage(message.getChannel(), "", getEmbed().build());
        ModFetcher.initHandlers();
        getEmbed().withDesc("Reload complete. That tickled ;)");
        sendMessage(message.getChannel(), "", getEmbed().build());
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
