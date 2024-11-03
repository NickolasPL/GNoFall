package org.gnofall.gnofall;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NoFallManager {
    private final GNoFall plugin;
    private final Map<UUID, Long> noFallPlayers = new HashMap<>();

    public NoFallManager(GNoFall plugin) {
        this.plugin = plugin;
    }

    public void setNoFall(Player player, int durationInSeconds) {
        noFallPlayers.put(player.getUniqueId(), System.currentTimeMillis() + (durationInSeconds * 1000L));
        new BukkitRunnable() {
            @Override
            public void run() {
                noFallPlayers.remove(player.getUniqueId());
            }
        }.runTaskLater(plugin, durationInSeconds * 20L);
    }

    public void removeNoFall(Player player) {
        noFallPlayers.remove(player.getUniqueId());
    }

    public boolean hasNoFall(Player player) {
        return noFallPlayers.containsKey(player.getUniqueId()) && noFallPlayers.get(player.getUniqueId()) > System.currentTimeMillis();
    }
}
