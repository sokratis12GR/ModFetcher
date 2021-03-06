package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.TDTUtils.RolePermissions.ADMIN;
import static com.sokratis12gr.modfetcher.util.TDTUtils.userPermissions;

public abstract class CommandAdmin implements Command {

    @Override
    public boolean isValidUsage(IMessage message) {
        return userPermissions(message, ADMIN);
    }
}