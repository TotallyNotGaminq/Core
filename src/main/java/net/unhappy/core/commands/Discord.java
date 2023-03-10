package net.unhappy.core.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Discord implements CommandExecutor {
    private final Core plugin = Core.getInstance();

    public Discord(Core plugin) {
        Objects.requireNonNull(plugin.getCommand("discord")).setExecutor(this);

    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        TextComponent message = new TextComponent(ColorChat.chat("&dClick Me!"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ColorChat.chat("&bClick to join Discord Server!"))));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/XkbVC7pPWh"));
        player.spigot().sendMessage(message);
        return true;
    }

}
