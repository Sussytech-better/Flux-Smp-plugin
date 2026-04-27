package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BloodFluxEvent extends FluxEvent {

    public BloodFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Blood Flux", "Increased PvP damage, better kill rewards");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void disable() {
        // Unregister events if possible, or just stop effects
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            event.setDamage(event.getDamage() * 1.5); // 50% more damage
        }
    }
}