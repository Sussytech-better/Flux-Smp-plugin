package com.sussytech.fluxsmp.managers;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import com.sussytech.fluxsmp.events.FluxEvent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class FluxManager {

    private final FluxSmpPlugin plugin;
    private final List<FluxEvent> events;
    private FluxEvent activeEvent;
    private BossBar fluxBar;
    private double fluxProgress = 0.0;
    private final Random random = new Random();

    // Event rotation durations
    private static final long NORMAL_EVENT_DURATION_TICKS = 240000L; // 10 Minecraft days
    private static final long PEACE_EVENT_DURATION_TICKS = 72000L;   // 3 Minecraft days

    public FluxManager(FluxSmpPlugin plugin) {
        this.plugin = plugin;
        this.events = FluxEvent.createDefaultEvents(plugin);
        this.fluxBar = Bukkit.createBossBar("Flux Bar", BarColor.PURPLE, BarStyle.SOLID);
        fluxBar.setVisible(true);
        updateFluxBar();
    }

    public void start() {
        // Select initial event
        selectRandomEvent();

        scheduleNextRotation();
        }.runTaskTimer(plugin, 1200L, 1200L); // Every minute
    }

    public void stop() {
        if (fluxBar != null) {
            fluxBar.removeAll();
        }
    }

    public void rotateEvent() {
        if (activeEvent != null) {
            activeEvent.disable();
        }
        selectRandomEvent();
    }

    private void scheduleNextRotation() {
        long duration = (activeEvent != null && "Peace Flux".equals(activeEvent.getName()))
            ? PEACE_EVENT_DURATION_TICKS
            : NORMAL_EVENT_DURATION_TICKS;

        new BukkitRunnable() {
            @Override
            public void run() {
                rotateEvent();
            }
        }.runTaskLater(plugin, duration);
    }

    private void selectRandomEvent() {
        FluxEvent newEvent = events.get(random.nextInt(events.size()));
        while (newEvent == activeEvent && events.size() > 1) {
            newEvent = events.get(random.nextInt(events.size()));
        }
        setActiveEvent(newEvent);
    }

    private void setActiveEvent(FluxEvent event) {
        this.activeEvent = event;
     

    private void scheduleNextRotation() {
        long duration = (activeEvent != null && "Peace Flux".equals(activeEvent.getName()))
            ? PEACE_EVENT_DURATION_TICKS
            : NORMAL_EVENT_DURATION_TICKS;

        new BukkitRunnable() {
            @Override
            public void run() {
                rotateEvent();
            }
        }.runTaskLater(plugin, duration);
    }   event.enable();

        // Broadcast to all players
        String title = "§6⚡ New Flux Event!";
        String subtitle = "§e" + event.getName();
        String chatMessage = "§6[Flux] §eA new Flux Event has begun: §6" + event.getName() + "§e - " + event.getDescription();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(title, subtitle, 10, 70, 20);
            player.sendMessage(chatMessage);
        }


        // Schedule next rotation with appropriate duration
        scheduleNextRotation();
        plugin.getLogger().info("Activated Flux Event: " + event.getName());
    }

    public void increaseFluxProgress(double amount) {
        fluxProgress += amount;
        if (fluxProgress >= 1.0) {
            fluxProgress = 0.0;
            rotateEvent(); // Trigger early rotation
        }
        updateFluxBar();
    }

    public void onPlayerKill() {
        increaseFluxProgress(0.05); // 5% per kill
    }

    public void onPlayerDeath() {
        increaseFluxProgress(0.03); // 3% per death
    }

    private void updateFluxBar() {
        fluxBar.setProgress(fluxProgress);
        fluxBar.setTitle(String.format("Flux Bar: %.1f%%", fluxProgress * 100));
    }

    public FluxEvent getActiveEvent() {
        return activeEvent;
    }

    public void showFluxBarToPlayer(Player player) {
        fluxBar.addPlayer(player);
    }

    public void hideFluxBarFromPlayer(Player player) {
        fluxBar.removePlayer(player);
    }
}