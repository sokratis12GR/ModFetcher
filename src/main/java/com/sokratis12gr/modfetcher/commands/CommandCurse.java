package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sokratis12gr.modfetcher.util.EmbedBase.getEmbed;
import static com.sokratis12gr.modfetcher.util.Utilities.*;
import static java.lang.String.format;

public class CommandCurse extends CommandUser {

    private String project;

    @Override
    public void processCommand(IMessage message, String[] args) {
        if (args.length > 1) {
            for (int index = 1; index < args.length; index++) {
                project = args[index];
                if (getDownloads() != null) {
                    getEmbed().withDescription("\n" + makeBold("Project: [" + project + "](" + "https://minecraft.curseforge.com/projects/" + project + ")") + "\n" + makeBold("Downloads") + ": " + getDownloads());
                    sendMessage(message.getChannel(), "" + message.getAuthor(), getEmbed().build());
                } else {
                    sendMessage(message.getChannel(), "Sorry couldn't find a project with the name " + project);
                }
            }
        } else if (args.length == 1) {
            sendMessage(message.getChannel(), "Please provide a project, like " + makeCodeBlock("$curse armorplus"));
        }
    }

    private String getDownloads() {
        String content;
        URLConnection connection;
        String projectURL;
        if (project.contains("%!@#$^&*\"\'")) {
            return null;
        } else {
            projectURL = format("https://minecraft.curseforge.com/projects/%s", project);
        }
        try {
            connection = new URL(projectURL).openConnection();
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
            return matches.get(2); //gets the downloads
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDescription() {
        return format("Basic information about the %s project", project);
    }

    @Override
    public String getThoroughDescription() {
        return this.getDescription();
    }

}
