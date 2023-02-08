package net.unhappy.core.ui;

import net.unhappy.core.utils.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class TestUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4*9;
    public static void initialize() {
        inventory_name = ColorChat.chat("&bTest GUI");
        inv = Bukkit.createInventory(null, inv_rows);
    }
    public static Inventory GUI (Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
        ColorChat.createItem(inv,"APPLE",1,1, "&cTest Item", "&7This is lore 1", "this is lore 2");
        toReturn.setContents(inv.getContents());
        return toReturn;
    }
    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase(ColorChat.chat("&cTest Item"))) {
            Bukkit.broadcastMessage("a");
        }
    }
}