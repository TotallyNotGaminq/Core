package net.unhappy.core.database;

import lombok.Getter;
import lombok.Setter;
import net.unhappy.core.Core;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    private Core plugin = Core.getInstance();

    private PlayerData data;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.data = new PlayerData(uuid, name);
    }
}