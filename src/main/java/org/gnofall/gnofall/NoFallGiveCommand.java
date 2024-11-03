package org.gnofall.gnofall;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoFallGiveCommand implements CommandExecutor {
    private final GNoFall plugin;
    private final NoFallManager noFallManager;

    public NoFallGiveCommand(GNoFall plugin, NoFallManager noFallManager) {
        this.plugin = plugin;
        this.noFallManager = noFallManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Использование: /nofallgive <игрок> <длительность>");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Игрок не найден.");
            return false;
        }
        try {
            int duration = Integer.parseInt(args[1]);
            noFallManager.setNoFall(target, duration);
            String message = plugin.getConfig().getString("messages.nofall-give")
                    .replace("%player%", target.getName())
                    .replace("%duration%", String.valueOf(duration));
            sender.sendMessage(ChatColor.GREEN + message);
            target.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.nofall-received")
                    .replace("%duration%", String.valueOf(duration)));
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Длительность должна быть числом.");
        }
        return true;
    }
}

