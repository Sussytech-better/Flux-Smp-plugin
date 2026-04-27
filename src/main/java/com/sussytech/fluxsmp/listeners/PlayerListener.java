package com.sussytech.fluxsmp.listeners;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final FluxSmpPlugin plugin;

    public PlayerListener(FluxSmpPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getFluxManager().showFluxBarToPlayer(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getFluxManager().hideFluxBarFromPlayer(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        plugin.getFluxManager().onPlayerDeath();

        if (killer != null) {
            plugin.getFluxManager().onPlayerKill();
        }
    }
}