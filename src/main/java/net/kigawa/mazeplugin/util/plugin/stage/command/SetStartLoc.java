package net.kigawa.mazeplugin.util.plugin.stage.command;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.PluginUtil;
import net.kigawa.bordgameplugin.util.plugin.stage.StageManager;
import net.kigawa.bordgameplugin.util.plugin.stage.command.StageCommandBase;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetStartLoc extends StageCommandBase {
    PluginBase plugin;

    public SetStartLoc(PluginBase kigawaPlugin, StageManager manager) {
        super(kigawaPlugin, manager);
        plugin = kigawaPlugin;
    }

    @Override
    public String getName() {
        return "setstartloc";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        //check args
        if (strings.length == 5) {
            //set start loc
            getManager().setStartLoc(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]),
                    commandSender);
            //send message
            return "set start loc";
        }
        return null;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public String errorMessage() {
        return "/stage setstartloc <game name> <x> <y> <z>";
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, org.bukkit.command.Command command, String label, String[] strings) {
        if (strings.length == 2) {
            return getManager().getStageNames();
        }
        if (strings.length == 3) {
            List<String> list = new ArrayList();
            Player player = PluginUtil.getPlayer(sender);
            Location loc = player.getLocation();
            list.add(Integer.toString(loc.getBlockX()) + loc.getBlockY() + loc.getBlockX());
            return list;
        }
        return null;
    }
}
