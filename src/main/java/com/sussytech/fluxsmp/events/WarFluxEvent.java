package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WarFluxEvent extends FluxEvent {

    public WarFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "War Flux", "PvP rewards boosted, combat encouraged");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void disable() {
        // Unregister events
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            // Give extra rewards to killer
            killer.giveExp(50);
            killer.sendMessage("§6[War Flux] §eBonus XP for the kill!");
        }
    }
}