package github.totorewa.paper.logtps;

import github.totorewa.paper.logtps.helpers.Color;
import github.totorewa.paper.logtps.helpers.HeatmapColorHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Locale;

public class LogTPSPlugin extends JavaPlugin {
    public final static int MSPT_THRESHOLD = 50;

    @Override
    public void onEnable() {
        super.onEnable();

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, (Runnable) new BukkitRunnable() {
            @Override
            public void run() {
                double[] serverTPS = Bukkit.getTPS();
                if (serverTPS.length > 0) {
                    sendTPS(serverTPS[0], Bukkit.getAverageTickTime());
                }
                if (isEnabled()) {
                    scheduler.scheduleSyncDelayedTask(LogTPSPlugin.this, (Runnable) this, 20L);
                }
            }
        });
    }

    private void sendTPS(double tps, double mspt) {
        TextColor color = HeatmapColorHelper.getColor(mspt, MSPT_THRESHOLD).textColor;
        Component footer = Component.text("TPS: ", Color.GRAY.textColor)
                .append(Component.text(formatDouble(tps), color))
                .append(Component.text(" MSPT: ", Color.GRAY.textColor))
                .append(Component.text(formatDouble(mspt), color));

        for (Player player : getServer().getOnlinePlayers()) {
            player.sendPlayerListFooter(footer);
        }
    }

    private String formatDouble(double d) {
        return String.format(Locale.US, "%.1f", d);
    }
}
