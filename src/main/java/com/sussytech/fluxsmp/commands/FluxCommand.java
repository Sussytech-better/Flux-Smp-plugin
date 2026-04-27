package com.sussytech.fluxsmp.commands;

import com.sussytech.fluxsmp.FluxSmpPlugin;
import com.sussytech.fluxsmp.events.FluxEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FluxCommand implements CommandExecutor {

    private final FluxSmpPlugin plugin;

    public FluxCommand(FluxSmpPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§6[Flux] §eUsage: /flux <info|reload>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "info":
                FluxEvent activeEvent = plugin.getFluxManager().getActiveEvent();
                if (activeEvent != null) {
                    sender.sendMessage("§6[Flux] §eCurrent Event: §6" + activeEvent.getName());
                    sender.sendMessage("§eDescription: " + activeEvent.getDescription());
                } else {
                    sender.sendMessage("§6[Flux] §eNo active event.");
                }
                break;
            case "reload":
                if (sender.hasPermission("flux.admin")) {
                    plugin.getFluxManager().rotateEvent();
                    sender.sendMessage("§6[Flux] §eEvent rotated!");
                } else {
                    sender.sendMessage("§cNo permission.");
                }
                break;
            default:
                sender.sendMessage("§6[Flux] §eUnknown subcommand.");
                break;
        }

        return true;
    }
}