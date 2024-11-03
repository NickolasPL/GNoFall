package org.gnofall.gnofall;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoFallOffCommand implements CommandExecutor {
    private final GNoFall plugin;
    private final NoFallManager noFallManager;

    public NoFallOffCommand(GNoFall plugin, NoFallManager noFallManager) {
        this.plugin = plugin;
        this.noFallManager = noFallManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (noFallManager.hasNoFall(player)) {
                noFallManager.removeNoFall(player);
                String message = plugin.getConfig().getString("messages.nofall-disabled");
                player.sendMessage(ChatColor.RED + message);
            } else {
                player.sendMessage(ChatColor.RED + "Режим NoFall и так выключен");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Эту команду может выполнить только игрок.");
        }
        return true;
    }
}
