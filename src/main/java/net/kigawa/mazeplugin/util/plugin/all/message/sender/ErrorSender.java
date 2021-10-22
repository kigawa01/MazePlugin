package net.kigawa.mazeplugin.util.plugin.all.message.sender;


import net.kigawa.bordgameplugin.util.all.Util;
import net.kigawa.bordgameplugin.util.plugin.all.message.Messenger;
import net.kigawa.bordgameplugin.util.plugin.all.message.sender.Sender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ErrorSender extends Sender {
    public ErrorSender(String title, Player player) {
        super(player);
        player.sendMessage(ChatColor.RED + title);
    }

    public ErrorSender(String title, List<Player> senders) {
        super(Util.changeListType(senders, CommandSender.class));
        Messenger.sendMessage(senders, title);
    }

    public static String getString(String message) {
        return ChatColor.RED + "Error: " + message;
    }
}
