package com.sussytech.fluxsmp;

import com.sussytech.fluxsmp.commands.FluxCommand;
import com.sussytech.fluxsmp.listeners.PlayerListener;
import com.sussytech.fluxsmp.managers.FluxManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FluxSmpPlugin extends JavaPlugin {

    private FluxManager fluxManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Flux SMP Plugin enabled!");

        // Initialize managers
        fluxManager = new FluxManager(this);
        fluxManager.start();

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        // Register commands
        getCommand("flux").setExecutor(new FluxCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (fluxManager != null) {
            fluxManager.stop();
        }
        getLogger().info("Flux SMP Plugin disabled!");
    }

    public FluxManager getFluxManager() {
        return fluxManager;
    }
}