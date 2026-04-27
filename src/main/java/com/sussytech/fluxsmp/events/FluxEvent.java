package com.sussytech.fluxsmp.events;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

public abstract class FluxEvent implements Listener {

    protected final FluxSmpPlugin plugin;
    protected final String name;
    protected final String description;

    public FluxEvent(FluxSmpPlugin plugin, String name, String description) {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
    }

    public abstract void enable();
    public abstract void disable();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static List<FluxEvent> createDefaultEvents(FluxSmpPlugin plugin) {
        return Arrays.asList(
            new BloodFluxEvent(plugin),
            new RichFluxEvent(plugin),
            new StormFluxEvent(plugin),
            new HuntFluxEvent(plugin),
            new PeaceFluxEvent(plugin),
            new DestructionFluxEvent(plugin),
            new ShadowFluxEvent(plugin),
            new WarFluxEvent(plugin),
            new ChaosFluxEvent(plugin),
            new FortuneFluxEvent(plugin),
            new GravityFluxEvent(plugin),
            new FreezeFluxEvent(plugin)
        );
    }
}