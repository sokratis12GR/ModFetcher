package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class CommandGuildRename extends CommandAdmin {

    @Override
    public void processCommand(IMessage message, String[] args) {
        IGuild guild = message.getGuild();
        String name = "";
        try {
            if (args.length > 1) {
                name = args[1];
                if (args.length > 2) {
                    name = name(args[1], args[2]);
                    if (args.length > 3) {
                        name = name(args[1], args[2], args[3]);
                        if (args.length > 4)
                            name = name(args[1], args[2], args[3], args[4]);
                    }
                }
            }
            guild.changeName(name);
        } catch (RateLimitException | DiscordException | MissingPermissionsException e) {
            e.printStackTrace();
        }
    }

    private String name(String... nameParts) {
        String text = "";
        for (String fullName : nameParts) {
            text += " " + fullName;
        }
        return text;
    }

    @Override
    public String getDescription() {
        return "Renames the guild";
    }
}
