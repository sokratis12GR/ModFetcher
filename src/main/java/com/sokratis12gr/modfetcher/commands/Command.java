package com.sokratis12gr.modfetcher.commands;

import com.sokratis12gr.modfetcher.util.Utilities;
import sx.blah.discord.handle.obj.IMessage;

public interface Command {

    /**
     * Checks if a command is valid. If not, it will not be executed.
     *
     * @param message The message which contains all the command information.
     *
     * @return boolean Whether or not the command should execute.
     */
    default boolean isValidUsage(IMessage message) {

        return true;
    }

    /**
     * Processes a command once it has been confirmed to be valid. This is where a command is
     * executed.
     *
     * @param message The message which contains all of the command information.
     */
    void processCommand(IMessage message, String[] params);

    /**
     * Provides a description of what the command does.
     *
     * @return String A description of the command being registered.
     */
    String getDescription();

    /**
     * Provides a description of how the command works. This is a in finer detail.
     *
     * @return String A description of how the command works.
     */
    String getThoroughDescription();

    /**
     * Can be called whenever the command fails. By default this is used for sending the in
     * depth argument description when a command is not entered properly.
     *
     * @param message The message which contains all of the command information.
     */
    default void onFail(IMessage message) {
        Utilities.sendPrivateMessage(message.getAuthor(), Utilities.makeMultiCodeBlock("The command failed!" + System.lineSeparator() + getThoroughDescription()));
    }
}
