package com.sokratis12gr.modfetcher.util;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.sokratis12gr.modfetcher.ModFetcher.instance;

public class Utilities {

    public static final String SEPERATOR = System.lineSeparator();

    private static Random random = new Random();

    public static File downloadFile(String site) {

        final File file = new File("Image" + random.nextInt() + ".png");

        try {

            FileUtils.copyURLToFile(new URL(site), file);
        } catch (final IOException e) {

            e.printStackTrace();
        }

        return file;
    }

    /**
     * Creates a ping message for a user based upon their user ID.
     *
     * @param userID The user ID of the user to generate a ping message for.
     *
     * @return String A short string which will ping the specified user when sent into the
     * chat.
     */
    public static String getPingMessage(String userID) {

        return "<@" + userID + ">";
    }

    /**
     * Makes a String message italicized. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the formatting codes applied.
     */
    public static String makeItalic(String message) {

        return "*" + message + "*";
    }

    /**
     * Makes a String message bold. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the bold formatting codes applied.
     */
    public static String makeBold(String message) {

        return "**" + message + "**";
    }

    /**
     * Makes a String message scratched out. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the scratched out formatting codes applied.
     */
    public static String makeScratched(String message) {

        return "~~" + message + "~~";
    }

    /**
     * Makes a String message underlined. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the underlined formatting codes applied.
     */
    public static String makeUnderlined(String message) {

        return "__" + message + "__";
    }

    /**
     * Makes a String message appear in a code block. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the code block format codes applied.
     */
    public static String makeCodeBlock(String message) {

        return "`" + message + "`";
    }

    /**
     * Makes a string which represents multiple lines of text.
     *
     * @param lines The lines of text to display. Each entry is a new line.
     *
     * @return A string which has been split up.
     */
    public static String makeMultilineMessage(String... lines) {

        String text = "";

        for (final String line : lines)
            text += line + SEPERATOR;

        return text;
    }

    /**
     * Makes a String message appear in a multi-lined code block. This only applies to chat.
     *
     * @param message The message to format.
     *
     * @return String The message with the multi-lined code block format codes applied.
     */
    public static String makeMultiCodeBlock(String message) {
        return "```" + message + "```";
    }

    public static String makeMultiJavaCodeBlock(String message) {
        return "```JAVA\n" + message + "```";
    }

    public static String makeMultiDiffCodeBlock(boolean isRemoved, String... lines) {
        String text = "";

        for (final String line : lines) {
            if (isRemoved) {
                text += "- " + line + SEPERATOR;
            } else {
                text += "+ " + line + SEPERATOR;
            }
        }
        return "```DIFF\n" + text + "```";
    }

    public static String makeMultiDiffCodeBlock(String message) {
        return "```DIFF\n" + message + "```";
    }

    public static String makeMultiHTMLCodeBlock(String message) {
        return "```HTML\n" + message + "```";
    }

    public static String makeMultiMarkDownCodeBlock(String message) {
        return "```MARKDOWN\n" + message + "```";
    }

    public static String makeErrorMessage(String... lines) {
        String text = "";
        for (final String line : lines) {
            text += "- " + line + SEPERATOR;
        }
        return "```DIFF\n" + text + "```";
    }

    public static String makeErrorMessage(String message) {
        return "```DIFF\n" + "- " + message + "```";
    }

    public static String makeSuccessMessage(String... lines) {
        String text = "";
        for (final String line : lines) {
            text += "+ " + line + SEPERATOR;
        }
        return "```DIFF\n" + text + "```";
    }

    public static String makeSuccessMessage(String message) {
        return "```DIFF\n" + "+ " + message + "```";
    }

    /**
     * Attempts to send a private message to a user. If a private message channel does not
     * already exist, it will be created.
     *
     * @param user    The user to send the private message to.
     * @param message The message to send to the user.
     */
    public static void sendPrivateMessage(IUser user, String message) {
        try {
            sendMessage(instance.getOrCreatePMChannel(user), message);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendPrivateMessage(IUser user, String message, EmbedObject object) {
        try {
            sendMessage(instance.getOrCreatePMChannel(user), message, object);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendPrivateMessage(IUser user, EmbedObject object) {
        try {
            sendMessage(instance.getOrCreatePMChannel(user), object);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message into the chat. This version of the method will handle exceptions for
     * you.
     *
     * @param channel The channel to send to message to.
     * @param message The message to send to the channel.
     */
    public static void sendMessage(IChannel channel, String message) {

        try {
            if (channel != channel.getGuild().getChannelByID("218315723473158146")) {
                channel.sendMessage(message);
            } else {
                channel.setTypingStatus(true);
            }
        } catch (MissingPermissionsException | DiscordException | RateLimitException e) {

            if (e instanceof DiscordException && e.toString().contains("String value is too long"))
                sendMessage(channel, "I tried to send a message, but it was too long.");

            else
                e.printStackTrace();
        }
    }

    public static void sendMessage(IChannel channel, String message, EmbedObject object) {

        if (message.length() > 2000 || object.description.length() > 2000) {

            Utilities.sendMessage(channel, "I tried to send a message, but it was too long. " + message.length() + "/2000 chars! Embedded: " + object.description.length() + "/2000!");
            Discord4J.LOGGER.info(message);
            Discord4J.LOGGER.info(object.description);
            return;
        }

        try {
            if (channel != channel.getGuild().getChannelByID("218315723473158146")) {
                channel.sendMessage(message, object, false);
                Thread.sleep(1000);
            } else {
                channel.setTypingStatus(true);
            }
        } catch (RateLimitException | DiscordException | MissingPermissionsException | InterruptedException e) {

            e.printStackTrace();
        }
    }

    public static void sendMessage(IChannel channel, EmbedObject object) {
        try {
            channel.sendMessage("", object, false);
            Thread.sleep(1000);
        } catch (RateLimitException | DiscordException | MissingPermissionsException | InterruptedException e) {

            e.printStackTrace();
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean invert) {

        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        if (invert)
            list = Lists.reverse(list);

        final Map<K, V> result = new LinkedHashMap<>();
        for (final Map.Entry<K, V> entry : list)
            result.put(entry.getKey(), entry.getValue());
        return result;
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        String output = "";
        for (Map.Entry<K, V> entry : map.entrySet())
            output += entry.getKey().toString() + " - " + entry.getValue().toString() + SEPERATOR;
        return output;
    }
}