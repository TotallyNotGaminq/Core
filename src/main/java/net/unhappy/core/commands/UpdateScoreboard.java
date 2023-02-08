package net.unhappy.core.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.unhappy.core.Core;
import net.unhappy.core.scoreboard.Board;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UpdateScoreboard implements CommandExecutor {

    private static final Core plugin = Core.getInstance();
    private final Board scoreboard = new Board();

    public UpdateScoreboard(Core plugin) {
        Objects.requireNonNull(plugin.getCommand("updatescoreboard")).setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            scoreboard.setScoreBoard(player);
        }, 20, 60);
        return true;
    }

}
