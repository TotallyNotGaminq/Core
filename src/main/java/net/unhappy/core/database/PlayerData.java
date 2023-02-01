package net.unhappy.core.database;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import net.unhappy.core.Core;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

@Getter
public class PlayerData {

    private Core plugin = Core.getInstance();

    private java.util.UUID UUID;
    private String playerName;
    private LoginData lastLogin = new LoginData();

    private Stat blocksMined = new Stat();

    public PlayerData(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
    }

    public void resetPlayer() {
        this.blocksMined.setAmount(0);
    }

    public void load(UUID uuid) {
        Document document = plugin.getServerCollection().find(Filters.eq("uuid", getUUID().toString())).first();
        if(document !=null) {
            this.blocksMined.setAmount(document.getInteger("blocksMined"));

        }
    }

    public void save() {
        Document document = new Document();
        document.put("name", getPlayerName().toLowerCase());
        document.put("realName", getPlayerName());
        document.put("uuid", getUUID().toString());
        document.put("blocksMined", this.blocksMined.getAmount());
        document.put("Last Login", this.lastLogin.getAmount()); //https://currentmillis.com/
        plugin.getServerCollection().replaceOne(Filters.eq("uuid", getUUID().toString()), document, new UpdateOptions().upsert(true));
    }





}