package net.kigawa.mazeplugin.util.plugin.all.message.logger;

import net.kigawa.bordgameplugin.util.plugin.all.message.logger.Logger;
import org.bukkit.ChatColor;

public class InfoLogger extends Logger {
    public InfoLogger(String title, net.kigawa.bordgameplugin.util.all.Logger logger) {
        super(logger);
        logger(ChatColor.GREEN + title);
    }
}
