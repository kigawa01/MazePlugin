package net.kigawa.mazeplugin.util.plugin.all.event;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import org.bukkit.event.Listener;

public class Event implements Listener {
    PluginBase plugin;
    public Event(PluginBase kigawaPlugin){
        plugin=kigawaPlugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
}
