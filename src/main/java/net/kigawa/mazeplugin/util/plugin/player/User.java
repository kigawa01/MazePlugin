package net.kigawa.mazeplugin.util.plugin.player;

import net.kigawa.mazeplugin.util.plugin.inventory.Storage;
import net.kigawa.mazeplugin.util.yaml.Yaml;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID uuid;
    private Player player;
    private boolean isOnline;
    private List<String> groupList = new ArrayList<>();

    public User(Player player) {
        this.player = player;
        uuid = player.getUniqueId();
        isOnline = true;
    }

    public User(OfflinePlayer player) {
        uuid = player.getUniqueId();
        isOnline = false;
    }

    public static void saveUserData(Player player, Yaml yaml) {
        Location l = player.getLocation();
        UserData data = new UserData(player.getUniqueId().toString(), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), l.getWorld().getName());
        yaml.save(data);
    }

    public boolean teleport(Location location) {
        if (isOnline) {
            return player.teleport(location);
        }
        UserData data = UserManager.loadData(uuid.toString());
        return data.teleport(location);
    }

    public InventoryView openInventory(Inventory inventory) {
        if (isOnline) {
            return player.openInventory(inventory);
        }
        return null;
    }

    public InventoryView openInventory(Storage storage) {
        return openInventory(storage.getInventory());
    }

    public void joinEvent(PlayerJoinEvent event) {
        player = event.getPlayer();
        isOnline = true;
    }

    public void leaveEvent() {
        isOnline = false;
    }

    public void addGroup(String name) {
        if (groupList.contains(name)) return;
        groupList.add(name);
    }

    public void removeGroup(String name) {
        if (groupList.contains(name)) {
            groupList.remove(name);
        }
    }

    public boolean containGroup(String name) {
        return groupList.contains(name);
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public Player getPlayer() {
        if (isOnline) {
            return player;
        }
        return null;
    }

    public boolean equals(UUID uuid) {
        return this.uuid.equals(uuid);
    }
}
