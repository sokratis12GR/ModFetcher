package com.sokratis12gr.modfetcher;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;

public class ModFetcher {

    public static final String COMMAND_KEY = "$";
    public static IDiscordClient instance;

    public static void main(String[] args) { // Main method
        try {
            instance = new ClientBuilder().withToken("token").login(true);
            instance.getDispatcher().registerListener(new ModFetcher());
            initHandlers();
        } catch (final DiscordException e) {
            e.printStackTrace();
        }
    }

    public static void initHandlers() {
        CommandHandler.initCommands();
        ResourceHandler.initTextures();
    }

    @EventSubscriber
    public void onMessageRecieved(MessageReceivedEvent event) {
        if (event.getMessage().getContent().startsWith(COMMAND_KEY))
            CommandHandler.attemptCommandTriggers(event.getMessage());
    }
}