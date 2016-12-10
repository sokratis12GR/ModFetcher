package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.Utilities.*;
import static java.lang.String.format;

public class CommandDownloads implements Command {

    private String mod;

    private String name;

    public CommandDownloads(String modid, String modName) {
        mod = modid;
        name = modName;
    }

    @Override
    public void processCommand(IMessage message, String[] args) {
        sendMessage(message.getChannel(), makeBold(name) + ": " + makeItalic(format("https://minecraft.curseforge.com/projects/%s", mod)) + "\n" + makeBold("Downloads") + ": " + makeItalic(getDownloads()));
    }

    public String getDownloads() {
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(format("https://minecraft.curseforge.com/projects/%s", mod)).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
            // the pattern we want to search for
            Pattern p = Pattern.compile("<div class=\"info-data\">(.+?)</div>", Pattern.MULTILINE);
            Matcher m = p.matcher(content);
            List<String> matches = new ArrayList<>();
            // print all the matches that we find
            while (m.find()) {
                matches.add(m.group(1));
            }
            String downloads = matches.get(2);
            return downloads;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDescription() {
        return format("Basic information about the %s mod", name);
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}
