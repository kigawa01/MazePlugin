package net.kigawa.mazeplugin.util.plugin.inventory;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class StorageManager implements Listener {
    private final List<Storage> storageList = new ArrayList<>();
    private final List<Menu> menuList = new ArrayList<>();
    private final PluginBase plugin;
    private final Server server;

    public StorageManager(PluginBase plugin) {
        this.plugin = plugin;
        server = plugin.getServer();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void addStorage(Storage storage) {
        if (storageList.contains(storage)) {
            return;
        }
        storageList.add(storage);
    }

    public void addMenu(Menu menu) {
        if (menuList.contains(menu)) {
            return;
        }
        menuList.add(menu);
    }

    public void removeStorage(Storage storage) {
        storageList.remove(storage);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        for (Menu menu : menuList) {
            menu.onClick(event);
        }
    }
}
