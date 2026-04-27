package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PeaceFluxEvent extends FluxEvent {

    public PeaceFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Peace Flux", "PvP disabled");
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
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            // Check if damage is from another player (direct or projectile)
            Player victim = (Player) event.getEntity();
            Player attacker = null;

            if (event.getDamager() instanceof Player) {
                // Direct PvP damage
                attacker = (Player) event.getDamager();
            } else if (event.getDamager() instanceof Projectile) {
                // Projectile damage - check if shot by a player
                Projectile projectile = (Projectile) event.getDamager();
                if (projectile.getShooter() instanceof Player) {
                    attacker = (Player) projectile.getShooter();
                }
            }

            // Cancel damage if it's PvP
            if (attacker != null && attacker != victim) {
                event.setCancelled(true);
            }
        }
    }
}