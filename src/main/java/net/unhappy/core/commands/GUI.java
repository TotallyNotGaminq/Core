package net.unhappy.core.commands;

import net.unhappy.core.Core;
import net.unhappy.core.ui.TestUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUI implements CommandExecutor {
    private Core plugin = Core.getInstance();
    public GUI(Core plugin) {
        plugin.getCommand("gui").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("cool.cool")) {
            p.openInventory(TestUI.GUI(p));
        }
        return false;
    }
}