package com.sokratis12gr.modfetcher.util;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Random;

public class EmbedBase {
    private static final EmbedBuilder EMBED_BUILDER = new EmbedBuilder();

    public static EmbedBuilder getEmbed() {
        Color[] colors = new Color[]{
                Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
                Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
        };
        Random rand = new Random();

        int randomColorInt = rand.nextInt(colors.length);

        Color randomColor = colors[randomColorInt];
        EMBED_BUILDER.withColor(randomColor);
        EMBED_BUILDER.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        EMBED_BUILDER.withFooterText("ModFetcher made by sokratis12GR");
        EMBED_BUILDER.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        return EMBED_BUILDER;
    }

    public static EmbedObject getBuild() {
        return getEmbed().build();
    }
}
