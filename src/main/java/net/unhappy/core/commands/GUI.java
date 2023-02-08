package net.unhappy.core.commands;

import net.unhappy.core.Core;
import net.unhappy.core.ui.TestUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GUI implements CommandExecutor {
    private final Core plugin = Core.getInstance();
    public GUI(Core plugin) {
        Objects.requireNonNull(plugin.getCommand("gui")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("cool.cool")) {
            p.openInventory(TestUI.GUI(p));
        }
        return false;
    }
}