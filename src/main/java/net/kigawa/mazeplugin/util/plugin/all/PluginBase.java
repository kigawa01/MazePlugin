package net.kigawa.mazeplugin.util.plugin.all;

import net.kigawa.mazeplugin.util.plugin.player.User;
import net.kigawa.mazeplugin.util.plugin.player.UserData;
import net.kigawa.mazeplugin.util.plugin.player.UserManager;
import net.kigawa.mazeplugin.util.all.HasEnd;
import net.kigawa.mazeplugin.util.all.Logger;
import net.kigawa.mazeplugin.util.plugin.all.command.FirstCommand;
import net.kigawa.mazeplugin.util.plugin.all.message.Messenger;
import net.kigawa.mazeplugin.util.plugin.all.player.PlayerGetter;
import net.kigawa.mazeplugin.util.plugin.all.player.Teleporter;
import net.kigawa.mazeplugin.util.plugin.all.recorder.Recorder;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class PluginBase extends JavaPlugin implements Logger, Listener {
    private boolean debug;
    private Recorder recorder;
    private PlayerGetter playerGetter;
    private Messenger messenger;
    private Teleporter teleporter;
    private final List<FirstCommand> commands = new ArrayList<>();
    private final List<HasEnd> hasEnds = new ArrayList<>();
    private UserManager userManager;

    public abstract void addConfigDefault(FileConfiguration config);

    public abstract void onStart();

    @Override
    public void onLoad() {
        logger("onLoad");
    }

    @Override
    public void onEnable() {
        logger("onEnable");
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("debug", false);
        config.addDefault("useDB", false);
        addConfigDefault(config);
        config.options().copyDefaults(true);
        this.saveConfig();
        debug = config.getBoolean("debug");
        getServer().getPluginManager().registerEvents(this, this);

        recorder = new Recorder(this);
        playerGetter = new PlayerGetter(this);
        messenger = new Messenger(this);
        teleporter = new Teleporter();
        userManager = new UserManager(this);

        onStart();
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
        UserData data = getUserManager().loadData(event.getPlayer().getUniqueId().toString());
        if (data != null) {
            Location location = new Location(getUserManager().getWorld(data.getWorld()), data.getX(), data.getY(), data.getZ(), data.getYaw(), data.getPith());
            event.getPlayer().teleport(location);
        }
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
        User.saveUserData(event.getPlayer(), getUserManager().getYaml());
    }

    @Override
    public void onDisable() {
        logger("onDisable");
        for (HasEnd hasEnd : hasEnds) {
            hasEnd.end();
        }
        userManager.onDisable();
    }

    public Teleporter getTeleporter() {
        return teleporter;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void logger(String message) {
        if (debug) {
            this.getLogger().info(message);
        }
    }

    public void logger(int message) {
        if (debug) {
            this.getLogger().info(Integer.toString(message));
        }
    }

    public void logger(boolean message) {
        logger(String.valueOf(message));
    }

    public void logger  (double message){
        logger(Double.toString(message));
    }

    public void addCommand(FirstCommand command) {
        commands.add(command);
        List<String> permission = new ArrayList<>();
        permission.add(getName());
        command.setPermission(permission);
    }

    public void addHasEnd(HasEnd hasEnd) {
        hasEnds.add(hasEnd);
    }

    public List<FirstCommand> getCommands() {
        return commands;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Recorder getRecorder() {
        return recorder;
    }

    public PlayerGetter getPlayerGetter() {
        return playerGetter;
    }
}
