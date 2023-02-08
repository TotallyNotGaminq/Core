package net.unhappy.core.listeners;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.unhappy.core.Core;
import net.unhappy.core.database.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {

    private static final Core plugin = Core.getInstance();

    public EntityDeath(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean getChance(int minimalChance) {
        Random random = new Random();
        return random.nextInt(99) + 1 <= minimalChance;
    }

    public void giveReward(Player player, int money, int xp, int shard, int shardChance) {
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        profile.getData().getMoney().increaseAmount(Math.round(money));
        player.giveExp(Math.round(xp));
        if (getChance(shardChance)) {
            profile.getData().getMoney().increaseAmount(shard);
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals("dungeon")) {
            Player player = event.getEntity().getKiller();
            event.getDrops().clear();
        }
    }

    @EventHandler
    public void onMythicDeath(MythicMobDeathEvent event) {
        Player player = (Player) event.getKiller();
        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());
        if (profile == null) {
            plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
            profile = plugin.getProfileManager().getProfile(player.getUniqueId());
            profile.getData().load(player.getUniqueId());
        }
        for(int i = 0; i < 10; i++) {
            if (event.getMob().getName().contains("["+ i +"]")) {
                giveReward(player, (int) ((i*1.5)+1), (int) ((i*1.5)+1), (int) ((i*1.5)+1),10); //REGULAR MOB CALCULATION
            }
        }

    }


}
