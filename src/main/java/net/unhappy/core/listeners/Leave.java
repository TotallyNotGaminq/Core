package net.unhappy.core.listeners;

import lombok.Getter;
import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import net.unhappy.core.database.ProfileManager;
import net.unhappy.core.database.Stat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Getter
public class Leave implements Listener {
    private static Core plugin = Core.getInstance();

    public Leave(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        if (profile == null) {
            plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
            profile = plugin.getProfileManager().getProfile(player.getUniqueId());
            profile.getData().load(player.getUniqueId());
        }
        profile.getData().getLastLogin().setAmount(player.getLastPlayed());
        profile.getData().save();

    }
}
