package net.unhappy.core.listeners;

import net.unhappy.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn implements Listener {

    public static Core plugin = Core.getInstance();
    public CreatureSpawn(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event)
    {
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG)
        {
            event.setCancelled(true);
        }
    }

}
