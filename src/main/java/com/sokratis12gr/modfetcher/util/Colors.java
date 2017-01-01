package com.sokratis12gr.modfetcher.util;

import java.awt.*;
import java.util.Random;

class Colors {

    private static Color[] colors = new Color[]{
            Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };

    private static Random rand = new Random();

    private static int randomColorInt = rand.nextInt(colors.length);

    static Color randomColor = colors[randomColorInt];

}
