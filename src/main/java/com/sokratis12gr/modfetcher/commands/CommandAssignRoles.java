package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.TDTRoles;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class CommandAssignRoles extends CommandAdmin {

    @Override
    public void processCommand(IMessage message, String[] params) {
        IGuild guild = message.getGuild();
        try {
            for (IUser user : guild.getUsers()) {
                user.addRole(TDTRoles.MEMBER.getRole());
            }
        } catch (MissingPermissionsException | RateLimitException | DiscordException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Provides information about the sender of the command";
    }
}
