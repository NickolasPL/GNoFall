package org.gnofall.gnofall;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoFallCommand implements CommandExecutor {
    private final GNoFall plugin;
    private final NoFallManager noFallManager;

    public NoFallCommand(GNoFall plugin, NoFallManager noFallManager) {
        this.plugin = plugin;
        this.noFallManager = noFallManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int duration = plugin.getConfig().getInt("nofall-duration", 60); // Default to 60 seconds
            noFallManager.setNoFall(player, duration);
            String message = plugin.getConfig().getString("messages.nofall-enabled")
                    .replace("%duration%", String.valueOf(duration));
            player.sendMessage(ChatColor.GREEN + message);
        } else {
            sender.sendMessage(ChatColor.RED + "Эту команду может выполнить только игрок.");
        }
        return true;
    }
}
