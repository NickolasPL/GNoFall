package org.gnofall.gnofall;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class GNoFall extends JavaPlugin implements Listener {

    private NoFallManager noFallManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        noFallManager = new NoFallManager(this);

        getCommand("nofall").setExecutor(new NoFallCommand(this, noFallManager));
        getCommand("nofallgive").setExecutor(new NoFallGiveCommand(this, noFallManager));
        getCommand("nofalloff").setExecutor(new NoFallOffCommand(this, noFallManager));
        getCommand("reload").setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("GNoFall включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GNoFall выключен!");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (noFallManager.hasNoFall(player)) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "Урон от падения отключен.");
                }
            }
        }
    }
}
