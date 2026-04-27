package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class DestructionFluxEvent extends FluxEvent {

    public DestructionFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Destruction Flux", "Explosions and TNT are stronger");
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
    public void onEntityExplode(EntityExplodeEvent event) {
        // Increase explosion power
        event.setYield(event.getYield() * 2.0f);
    }
}