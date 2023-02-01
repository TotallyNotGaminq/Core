package net.unhappy.core.listeners;


import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import net.unhappy.core.database.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private static Core plugin = Core.getInstance();

    public BlockBreak(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        if (profile == null) {
            plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
            profile = plugin.getProfileManager().getProfile(player.getUniqueId());
            profile.getData().load(player.getUniqueId());
        }
        profile.getData().getBlocksMined().increaseAmount(1);
    }


}
