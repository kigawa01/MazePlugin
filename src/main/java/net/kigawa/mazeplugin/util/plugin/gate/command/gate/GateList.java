package net.kigawa.mazeplugin.util.plugin.gate.command.gate;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.PluginUtil;
import net.kigawa.bordgameplugin.util.plugin.gate.GateManager;
import net.kigawa.bordgameplugin.util.plugin.gate.command.gate.GateCommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GateList extends GateCommandBase {
    public GateList(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin, gate);
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            Player player = PluginUtil.getPlayer(commandSender);
            if (player != null) {
                getManager().sendGates(player);
                return "";
            }
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/gate list";
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}
