package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class HuntFluxEvent extends FluxEvent {

    public HuntFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Hunt Flux", "Players track nearest player with compass");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        // Start tracking task
        new BukkitRunnable() {
            @Override
            public void run() {
                updateCompasses();
            }
        }.runTaskTimer(plugin, 0L, 20L); // Every second
    }

    @Override
    public void disable() {
        // Stop tracking
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        giveCompass(event.getPlayer());
    }

    private void giveCompass(Player player) {
        if (!player.getInventory().contains(Material.COMPASS)) {
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
        }
    }

    private void updateCompasses() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().contains(Material.COMPASS)) {
                Player nearest = findNearestPlayer(player);
                if (nearest != null) {
                    player.setCompassTarget(nearest.getLocation());
                }
            }
        }
    }

    private Player findNearestPlayer(Player player) {
        Player nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (!other.equals(player)) {
                double distance = player.getLocation().distance(other.getLocation());
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearest = other;
                }
            }
        }
        return nearest;
    }
}