package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ShadowFluxEvent extends FluxEvent {

    public ShadowFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Shadow Flux", "Reduced visibility / fog-like effects");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        // Apply blindness effect to all players
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0, false, false));
                }
            }
        }.runTaskTimer(plugin, 0L, 80L); // Every 4 seconds
    }

    @Override
    public void disable() {
        // Effects will wear off naturally
    }
}