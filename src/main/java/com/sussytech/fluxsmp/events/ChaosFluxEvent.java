package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ChaosFluxEvent extends FluxEvent {

    private final Random random = new Random();

    public ChaosFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Chaos Flux", "Random effects every few minutes");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        // Apply random effects every 5 minutes
        new BukkitRunnable() {
            @Override
            public void run() {
                applyRandomEffect();
            }
        }.runTaskTimer(plugin, 6000L, 6000L); // Every 5 minutes
    }

    @Override
    public void disable() {
        // Effects will wear off
    }

    private void applyRandomEffect() {
        PotionEffectType[] effects = {
            PotionEffectType.SPEED, PotionEffectType.SLOWNESS, PotionEffectType.JUMP_BOOST,
            PotionEffectType.STRENGTH, PotionEffectType.INSTANT_HEALTH, PotionEffectType.POISON
        };

        for (Player player : Bukkit.getOnlinePlayers()) {
            PotionEffectType effect = effects[random.nextInt(effects.length)];
            player.addPotionEffect(new PotionEffect(effect, 600, 1)); // 30 seconds
        }
    }
}