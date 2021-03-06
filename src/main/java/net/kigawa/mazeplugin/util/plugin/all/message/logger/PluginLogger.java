package net.kigawa.mazeplugin.util.plugin.all.message.logger;

import net.kigawa.bordgameplugin.util.all.Logger;
import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;

public abstract class PluginLogger implements Logger {
    PluginBase plugin;

    public PluginLogger(PluginBase kigawaPlugin) {
        plugin = kigawaPlugin;
    }

    @Override
    public void logger(String message) {
        plugin.logger(message);
    }
}
