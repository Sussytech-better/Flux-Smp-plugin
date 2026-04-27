package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StormFluxEvent extends FluxEvent {

    public StormFluxEvent(FluxSmpPlugin plugin) {
        super(plugin, "Storm Flux", "Constant thunderstorms + stronger mobs");
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        // Set weather to storm in all worlds
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(true);
            world.setThundering(true);
            world.setWeatherDuration(Integer.MAX_VALUE);
        }
    }

    @Override
    public void disable() {
        // Reset weather
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(false);
            world.setThundering(false);
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof org.bukkit.entity.Monster) {
            // Make mobs stronger
            entity.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, Integer.MAX_VALUE, 1));
        }
    }
}