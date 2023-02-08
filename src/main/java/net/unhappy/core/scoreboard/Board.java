package net.unhappy.core.scoreboard;

import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import static org.bukkit.Bukkit.getServer;

public class Board {
    private final Core plugin = Core.getInstance();

    public void setScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Scoreboard", "dummy",
                ColorChat.chat("&bInDevStage.minehut.gg"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(ColorChat.chat("&9Online: &4" + Bukkit.getOnlinePlayers().size()
        + "&c/&4" + Bukkit.getMaxPlayers()));
        score.setScore(3);
        if (plugin.getProfileManager().getProfile(player.getUniqueId()) == null) {
            plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
            Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
            profile.getData().load(player.getUniqueId());
        }
        Score score2 = obj.getScore(ColorChat.chat("&a$" + plugin.getProfileManager().getProfile(player.getUniqueId()).getData().getMoney().getAmount()));
        score2.setScore(2);
        Score score3 = obj.getScore(ColorChat.chat("&a❖" + plugin.getProfileManager().getProfile(player.getUniqueId()).getData().getShard().getAmount()));
        score3.setScore(2);
        Score score4 = obj.getScore(ColorChat.chat("&d/Discord"));
        score4.setScore(0);
        player.setScoreboard(board);
    }
}
