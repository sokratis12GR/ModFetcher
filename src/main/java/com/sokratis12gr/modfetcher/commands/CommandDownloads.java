package com.sokratis12gr.modfetcher.commands;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
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

    private String project;

    @Override
    public void processCommand(IMessage message, String[] args) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        embed.withAuthorUrl(format("https://minecraft.curseforge.com/members/%s", message.getAuthor().getName()));
        embed.withColor(Color.CYAN);
        embed.withFooterText("\nModFetcher made by sokratis12GR");
        embed.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");

        if (args.length > 1) {
            for (int index = 1; index < args.length; index++) {
                project = args[index];
                if (getDownloads() != null) {
                    embed.withDescription("\n" + makeBold("Project: [" + project + "](" + "https://minecraft.curseforge.com/projects/" + project + ")") + "\n" + makeBold("Downloads") + ": " + getDownloads());
                    sendMessage(message.getChannel(), "" + message.getAuthor(), embed.build());
                } else {
                    embed.withDescription("Sorry couldn't find a project with the name " + project);
                    sendMessage(message.getChannel(), "", embed.build());
                }
            }
        } else if (args.length == 1) {
            embed.withDescription("Please provide a project, like " + makeCodeBlock("$curse armorplus"));
            sendMessage(message.getChannel(), "", embed.build());
        }
    }

    private String getDownloads() {
        String content;
        URLConnection connection;
        String projectURL = format("https://minecraft.curseforge.com/projects/%s", project);
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
            String downloads = matches.get(2);
            return downloads;
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
