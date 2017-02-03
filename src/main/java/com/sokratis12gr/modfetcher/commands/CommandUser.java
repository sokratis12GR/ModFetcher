package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.TDTUtils.RolePermissions.MEMBER;
import static com.sokratis12gr.modfetcher.util.TDTUtils.userPermissions;

public abstract class CommandUser implements Command {

    @Override
    public boolean isValidUsage(IMessage message) {
        return userPermissions(message, MEMBER);
    }
}