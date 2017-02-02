package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * ModFetcher created by sokratis12GR
 * - TheDragonTeam
 */
public class CommandUnMute implements Command {

    @Override
    public void processCommand(IMessage message, String[] args) {
        if (args.length > 1) {
            if (args[1] != null) {
                try {
                    IUser user;
                    String id;
                    IGuild guild = message.getGuild();
                    if (args[1].contains("@")) {
                        id = (args[1]).replace("<", "").replace("@", "").replace("!", "").replace(">", "");
                        user = message.getGuild().getUserByID(id);
                        Utilities.sendMessage(message.getChannel(), "ID: " + id);
                        guild.setMuteUser(user, false);
                    } else {
                        id = args[1];
                        user = message.getGuild().getUserByID(id);
                        Utilities.sendMessage(message.getChannel(), "ID: " + id);
                        guild.setMuteUser(user, false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "";
    }
}
