package net.unhappy.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ColorChat {
    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack createItem(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item;
        List<String> lore = new ArrayList();
        item = new ItemStack(Material.matchMaterial(material), amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorChat.chat(displayName));
        for (String s : loreString) {
            lore.add(ColorChat.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static ItemStack createItemByte(Inventory inv, String material, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item;
        List<String> lore = new ArrayList();
        item = new ItemStack(Material.matchMaterial(material), amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorChat.chat(displayName));
        for (String s : loreString) {
            lore.add(ColorChat.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }
}
