package net.unhappy.core.database;

import com.mongodb.Mongo;
import net.unhappy.core.Core;
import net.unhappy.core.Manager;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager extends Manager {

    private Map<UUID, Profile> profiles = new HashMap<>();

    public ProfileManager(Core plugin) {
        super(plugin);
    }

    public void handleProfileCreation(UUID uuid, String name) {
        if (!this.profiles.containsKey(uuid)) {
            profiles.put(uuid, new Profile(uuid, name));
        }
    }

    public Profile getProfile(Object object) {
        if (object instanceof Player player) {
            return profiles.getOrDefault(player.getUniqueId(), null);
        }
        if (object instanceof UUID uuid) {
            return profiles.getOrDefault(uuid, null);
        }
        if (object instanceof String) {
            return this.profiles.values().stream().filter(profile -> profile.getPlayerName().equalsIgnoreCase(object.toString())).findFirst().orElse(null);
        }
        return null;
    }

    public Map<UUID, Profile> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(Map<UUID, Profile> profiles) {
        this.profiles = profiles;
    }
}
