package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RichFluxEvent extends FluxEvent {

    private final Random random = new Random();

    public RichFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Rich Flux", "Higher ore spawn rates and mining loot");
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
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        if (isOre(blockType)) {
            // Chance to drop extra ore
            if (random.nextDouble() < 0.3) { // 30% chance
                event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(),
                    new ItemStack(blockType, 1)
                );
            }
        }
    }

    private boolean isOre(Material material) {
        return material == Material.DIAMOND_ORE || material == Material.GOLD_ORE ||
               material == Material.IRON_ORE || material == Material.COAL_ORE ||
               material == Material.LAPIS_ORE || material == Material.REDSTONE_ORE ||
               material == Material.EMERALD_ORE;
    }
}