package net.unhappy.core.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import net.unhappy.core.database.ProfileManager;
import net.unhappy.core.scoreboard.Board;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private static Core plugin = Core.getInstance();
    private Board scoreboard = new Board();

    public  Join(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        profile.getData().load(player.getUniqueId());
        profile.getData().getLastLogin().setAmount(player.getLastPlayed());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.broadcastMessage(ColorChat.chat("&bWelcome to 【In Dev Stage】 " + player.getName())));
        }, 1);
        scoreboard.setScoreBoard(player);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            scoreboard.setScoreBoard(player);
        }, 20, 60);
    }
}
