package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.TDTRoles;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public abstract class CommandUser implements Command {

    @Override
    public boolean isValidUsage(IMessage message) {

        final IUser user = message.getAuthor();

        return TDTRoles.EVERYONE.hasRole(user);
    }
}