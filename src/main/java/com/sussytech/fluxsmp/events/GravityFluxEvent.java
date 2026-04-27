package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

public class GravityFluxEvent extends FluxEvent {

    public GravityFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Gravity Flux", "Modified knockback and jump physics");
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
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // Modify knockback
            Vector velocity = player.getVelocity();
            velocity.multiply(2.0); // Double knockback
            player.setVelocity(velocity);
        }
    }
}