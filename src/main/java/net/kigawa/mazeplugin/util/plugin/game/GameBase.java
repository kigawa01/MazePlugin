package net.kigawa.mazeplugin.util.plugin.game;

import net.kigawa.mazeplugin.util.plugin.player.User;
import net.kigawa.mazeplugin.util.plugin.player.UserManager;
import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.all.message.sender.ErrorSender;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class GameBase<D extends GameDataBase, M extends GameManagerBase, U extends User> {
    private final D data;
    private final M manager;
    private final PluginBase plugin;
    private final UserManager<U> userManager;
    private final World world;
    private boolean timer;
    private int tick = 0;
    private int sec = 0;
    private int min = 0;

    public GameBase(D data, M manager) {
        this.data = data;
        this.manager = manager;
        plugin = manager.getPlugin();
        userManager = new UserManager<U>(plugin);
        world = plugin.getServer().getWorld(data.getWorld());

        start();
        timer();
    }

    public abstract U getUser(Player player);

    public abstract String onStart();

    public abstract String onEnd();

    public abstract void tickTimer();

    public abstract void secTimer();

    public abstract void minTimer();

    public void timer() {
        if (timer) {
            tickTimer();
            tick++;

            if (tick >= 20) {
                secTimer();
                sec++;

                if (sec >= 60) {
                    minTimer();
                    min++;
                }
            }
            Bukkit.getScheduler().runTaskLater(plugin, this::timer, 1);
        }
    }

    public void stopTimer() {
        timer = false;
    }

    public String end() {
        stopTimer();
        return onEnd();
    }

    public String start() {
        if (world==null){
            return ErrorSender.getString(data.getWorld()+"is not exit");
        }
        for (Player player : world.getPlayers()) {
            userManager.addUser(getUser(player));
        }
        return onStart();
    }

    public boolean equals(String name) {
        return data.getName().equals(name);
    }

    public PluginBase getPlugin() {
        return plugin;
    }

    public UserManager<U> getUserManager() {
        return userManager;
    }

    public M getManager() {
        return manager;
    }

    public D getData() {
        return data;
    }
}
