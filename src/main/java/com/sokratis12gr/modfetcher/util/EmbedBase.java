package com.sokratis12gr.modfetcher.util;

import sx.blah.discord.util.EmbedBuilder;

import static com.sokratis12gr.modfetcher.util.Colors.randomColor;

public class EmbedBase {

    private static final EmbedBuilder EMBED_BUILDER = new EmbedBuilder();

    public static EmbedBuilder getEmbed() {
        EMBED_BUILDER.withThumbnail("https://sokratis12gr.com/uploads/ModFetcher.png");
        EMBED_BUILDER.withFooterText("\nModFetcher made by sokratis12GR");
        EMBED_BUILDER.withFooterIcon("https://sokratis12gr.com/img/logo-min.png");
        EMBED_BUILDER.withColor(randomColor);
        return EMBED_BUILDER;
    }
}
