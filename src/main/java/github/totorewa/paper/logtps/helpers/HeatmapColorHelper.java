package github.totorewa.paper.logtps.helpers;

public class HeatmapColorHelper {
    private HeatmapColorHelper() {}

    public static Color getColor(double value, double max) {
        if (value > max) return Color.LIGHT_PURPLE;
        if (value > 0.8 * max) return Color.RED;
        if (value > 0.5 * max) return Color.YELLOW;
        if (value >= 0.0D) return Color.DARK_GREEN;
        return Color.GRAY;
    }
}
