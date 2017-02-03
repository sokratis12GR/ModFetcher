package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

/**
 * ModFetcher created by sokratis12GR
 * - TheDragonTeam
 */
public class CommandWrite implements Command {

    @Override
    public void processCommand(IMessage message, String[] args) {
        String build = "";
        if (args.length > 1) {
            if (args[1] != null && args.length > 2) {
                String comment = message.getContent().substring(args[0].length() + args[1].length() + 3)/*.replace("\\n", "\n").replace("\\t", "\t")*/;
                build = Utilities.makeMultiTypeMessage(args[1], comment);
            }
            Utilities.sendMessage(message.getChannel(), build);
        }
    }

    @Override
    public String getDescription() {
        return "";
    }
}
