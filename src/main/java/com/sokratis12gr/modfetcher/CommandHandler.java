package com.sokratis12gr.modfetcher;

import com.sokratis12gr.modfetcher.commands.*;
import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    /**
     * A Map containing all registered commands and their keys.
     */
    private static Map<String, Command> commands = new HashMap<>();

    /**
     * A registry message used to initialize all of the commands, and register them.
     */
    public CommandHandler() {
        commands.clear();
        commands.put("help", new CommandHelp());
        commands.put("about", new CommandAbout());
        commands.put("members", new CommandMemberCount());
        commands.put("curse", new CommandCurse());
        commands.put("self", new CommandMe());
        commands.put("server", new CommandServer());
        commands.put("calc", new CommandCalculate());
        commands.put("id", new CommandID());
        commands.put("write", new CommandWrite());
        commands.put("mute", new CommandMute());
        commands.put("unmute", new CommandUnMute());
        commands.put("check", new CommandCheck());
        commands.put("new", new CommandAssignRoles());
    }

    /**
     * Registers a command with the command registry.
     *
     * @param key     The key that will trigger the command.
     * @param command The command that is triggered by the key phrase.
     */
    public static void registerCommand(String key, Command command) {
        if (!commands.containsKey(key))
            commands.put(key, command);
    }

    /**
     * Provides access the the Map used to store all registered commands.
     *
     * @return The Map used to store all commands.
     */
    public static Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Attempts to trigger a command in the command registry.
     *
     * @param message The message to parse.
     */
    public static void attemptCommandTriggers(IMessage message) {
        final String key = getCommandKeyFromMessage(message.getContent());
        final Command command = commands.get(key);

        if (command == null) {
            Utilities.sendMessage(message.getChannel(), "No command was found by that name. " + key);
            return;
        }

        if (!command.isValidUsage(message)) {

            Utilities.sendPrivateMessage(message.getAuthor(), "You do not have permission to use the " + key + " command. Please try again, or look into getting elevated permissions.");
            return;
        }

        command.processCommand(message, getParameters(message.getContent()));
    }

    /**
     * Retrieves a command key from an IMessage. This method assumes that the message passed
     * starts with a command character.
     *
     * @param message The contents of the message to retrieve the key from.
     *
     * @return String The command key being used.
     */
    public static String getCommandKeyFromMessage(String message) {
        return message.substring(1).split(" ")[0].toLowerCase();
    }

    /**
     * Generates a list of String parameters used for a command, based on a String message.
     * This method assumes that the message passed starts with a command character. This method
     * will also use the command key as the first parameter.
     *
     * @param message The message to sort through.
     *
     * @return String[] An array of all parameters and the command key.
     */
    public static String[] getParameters(String message) {
        return message.substring(1).split(" ");
    }

    /**
     * Gets a command with the passed key name.
     *
     * @param keyName The name of the command to look for.
     *
     * @return Command The found command, or null if it doesn't exist.
     */
    public static Command getCommand(String keyName) {
        return commands.get(keyName.toLowerCase());
    }
}