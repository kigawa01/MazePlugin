package net.kigawa.mazeplugin.util.plugin.all.message.sender;

import net.kigawa.mazeplugin.util.all.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class InfoSender extends Sender {

    public InfoSender(String title, CommandSender sender) {
        super(sender);
        sender.sendMessage(ChatColor.GREEN + title);
    }

    public InfoSender(String title, List<Player> players) {
        super(Util.changeListType(players, CommandSender.class));
        sendMessage(Util.changeListType(players, CommandSender.class), ChatColor.GREEN + title);
    }
}
