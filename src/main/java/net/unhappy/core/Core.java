package net.unhappy.core;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import net.unhappy.core.commands.Discord;
import net.unhappy.core.commands.GUI;
import net.unhappy.core.commands.UpdateScoreboard;
import net.unhappy.core.database.Profile;
import net.unhappy.core.database.ProfileManager;
import net.unhappy.core.listeners.*;
import net.unhappy.core.scoreboard.Board;
import net.unhappy.core.ui.TestUI;
import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public final class Core extends JavaPlugin implements Listener {
    @Getter
    public static Core instance;
    private ProfileManager profileManager;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> serverCollection;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;;
        System.setProperty("DEBUG.GO", "true");
        System.setProperty("DB.TRACE", "true");
        Logger mongoLobber = Logger.getLogger("org.mongodb.driver");
        mongoLobber.setLevel(Level.WARNING);
        getServer().getPluginManager().registerEvents(this, this);
        //connect to the server/database/collection
        mongoClient = MongoClients.create("mongodb+srv://test:test@playerdata.hgn2lzs.mongodb.net/?retryWrites=true&w=majority");
        mongoDatabase = mongoClient.getDatabase("core");
        serverCollection = mongoDatabase.getCollection("playerdata");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Connected to MongoDB!");
        profileManager = new ProfileManager(this);
        new Board();
        new Join(this);
        new Leave(this);
        new BlockBreak(this);
        new InventoryClickListener(this);
        new CreatureSpawn(this);
        new GUI(this);
        new Discord(this);
        new UpdateScoreboard(this);
        new EntityDeath(this);
        TestUI.initialize();
        Bukkit.broadcastMessage(ColorChat.chat("&a&lCore has been updated."));

    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            Profile profile = getProfileManager().getProfile(player.getUniqueId());
            if (profile == null) {
                this.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
                profile = this.getProfileManager().getProfile(player.getUniqueId());
                profile.getData().load(player.getUniqueId());
            }
            profile.getData().getLastLogin().setAmount(player.getLastPlayed());
            profile.getData().save();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, @NotNull String[] args) {
        String commandName = command.getName().toLowerCase();
        if(commandName.equals("test")) {
            Player player = (Player) sender;
            Profile profile = getProfileManager().getProfile(player.getUniqueId());
            profile.getData().save();
        }
        return true;
    }
}
