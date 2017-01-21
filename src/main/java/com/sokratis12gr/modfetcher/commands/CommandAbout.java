package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.sendPrivateMessage;

public class CommandAbout extends CommandUser {

    @Override
    public void processCommand(IMessage message, String[] args) {
        final String about = Utilities.makeMultilineMessage(String.format("\nJava Version: %s", System.getProperty("java.version")), String.format("OS: %s", System.getProperty("os.name")), String.format("Country: %s", System.getProperty("user.country")), "Owner: sokratis12GR", "Birth Date: 2016-11-22", "Source: [GitHub](https://github.com/sokratis12GR/ModFetcher)");
        getEmbed().withDesc("Hello! I am ModFetcher, I'm used to provide info about your needs " + about);
        sendPrivateMessage(message.getAuthor(), "", getEmbed().build());
    }

    @Override
    public String getDescription() {
        return "Shares a list of information about the bot, including system specs and authorship info.";
    }
}