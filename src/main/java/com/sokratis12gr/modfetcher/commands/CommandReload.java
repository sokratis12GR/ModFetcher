package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.ModFetcher;
import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public class CommandReload implements Command {

    @Override
    public void processCommand(IMessage message, String[] params) {
        Utilities.sendMessage(message.getChannel(), "Reloading handlers and resource!");
        ModFetcher.initHandlers();
        Utilities.sendMessage(message.getChannel(), "Reload complete. That tickled ;)");
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
