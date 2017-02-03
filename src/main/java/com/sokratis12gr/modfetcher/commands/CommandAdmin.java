package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.TDTUtils;
import sx.blah.discord.handle.obj.IMessage;

import static com.sokratis12gr.modfetcher.util.TDTUtils.RolePermissions.ADMIN;

public abstract class CommandAdmin implements Command {

    @Override
    public boolean isValidUsage(IMessage message) {
        return TDTUtils.userPermissions(message, ADMIN);
    }
}