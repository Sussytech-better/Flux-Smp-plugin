package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FortuneFluxEvent extends FluxEvent {

    private final Random random = new Random();

    public FortuneFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Fortune Flux", "Increased loot from mobs and chests");
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
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof org.bukkit.entity.Player) {
            // Chance to double drops
            if (random.nextDouble() < 0.5) {
                for (ItemStack drop : event.getDrops()) {
                    drop.setAmount(drop.getAmount() * 2);
                }
            }
        }
    }
}