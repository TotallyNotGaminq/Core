package net.unhappy.core.scoreboard;

import net.unhappy.core.Core;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import static org.bukkit.Bukkit.getServer;

public class Board {
    private Core plugin = Core.getInstance();

    public void setScoreBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Scoreboard", "dummy",
                ColorChat.chat("&bInDevStage.minehut.gg"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(ColorChat.chat("&9Online: &4" + Bukkit.getOnlinePlayers().size()
        + "&c/&4" + Bukkit.getMaxPlayers()));
        score.setScore(2);
        Score score2 = obj.getScore(ColorChat.chat("&a$" + plugin.getProfileManager().getProfile(player.getUniqueId()).getData().getBlocksMined().getAmount()));
        score2.setScore(1);
        Score score3 = obj.getScore(ColorChat.chat("&d/Discord"));
        score3.setScore(0);
        player.setScoreboard(board);
    }
}
