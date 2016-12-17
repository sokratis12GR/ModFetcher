package com.sokratis12gr.modfetcher;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ModFetcher {

    public static final String COMMAND_KEY = "$";
    public static IDiscordClient instance;
    public static FileReader fileReader;

    private static String token_key;

    public static void main(String[] args) { // Main method
        File token = new File("token.txt");
        try {
            token_key = Files.readAllLines(token.toPath(), StandardCharsets.UTF_8).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            instance = new ClientBuilder().withToken(token_key).login();
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