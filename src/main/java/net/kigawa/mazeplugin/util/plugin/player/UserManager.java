package net.kigawa.mazeplugin.util.plugin.player;

import net.kigawa.mazeplugin.util.all.Logger;
import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.yaml.Yaml;
import net.kigawa.mazeplugin.util.yaml.YamlData;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager<U extends User> implements Listener {
    private static Yaml yaml;
    private final List<U> userList = new ArrayList<>();
    private final PluginBase plugin;

    public UserManager(PluginBase plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        yaml = getUserYaml();
        this.plugin = plugin;
    }

    public static Yaml getUserYaml() {
        if (yaml == null) {
            File dir = Paths.get("").toAbsolutePath().toFile();
            File user = new File(dir, "user");
            yaml = new Yaml(user, new Logger() {
                @Override
                public void logger(String message) {
                }
            });
        }
        return yaml;
    }

    public static UserData loadData(String name) {
        return getUserYaml().load(UserData.class, name);
    }

    public List<U> getGroup(String name) {
        List<U> userList = new ArrayList<>();
        for (U user : this.userList) {
            if (user.containGroup(name)) {
                userList.add(user);
            }
        }
        return userList;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
        U user = getUser(event.getPlayer().getUniqueId());
        if (user == null) return;
        user.joinEvent(event);
    }

    public void onDisable() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            User.saveUserData(player, yaml);
        }
    }

    public void saveData(YamlData data) {
        yaml.save(data);
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
        U user = getUser(event.getPlayer().getUniqueId());
        if (user == null) return;
        user.leaveEvent();
        User.saveUserData(event.getPlayer(), yaml);
    }

    public List<U> addUsers(List<U> userList) {
        this.userList.addAll(userList);
        return this.userList;
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public User addUser(U user) {
        userList.add(user);
        return user;
    }

    public World getWorld(String name) {
        return plugin.getServer().getWorld(name);
    }

    public PluginBase getPlugin() {
        return plugin;
    }

    public Yaml getYaml() {
        return yaml;
    }

    public U getUser(UUID uuid) {
        for (U user : userList) {
            user.equals(uuid);
        }
        return null;
    }

    public List<U> getUserList() {
        return userList;
    }
}
