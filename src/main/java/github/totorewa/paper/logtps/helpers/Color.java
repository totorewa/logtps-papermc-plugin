package github.totorewa.paper.logtps.helpers;

import net.kyori.adventure.text.format.TextColor;

public enum Color {
    DARK_GREEN(43520),
    GRAY(11184810),
    LIGHT_PURPLE(16733695),
    RED(16733525),
    YELLOW(16777045);

    public final TextColor textColor;

    Color(int color) {
        textColor = TextColor.color(color);
    }
}
