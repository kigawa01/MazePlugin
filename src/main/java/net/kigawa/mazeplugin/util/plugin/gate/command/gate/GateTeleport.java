package net.kigawa.mazeplugin.util.plugin.gate.command.gate;

import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.all.PluginUtil;
import net.kigawa.bordgameplugin.util.plugin.gate.GateManager;
import net.kigawa.bordgameplugin.util.plugin.gate.command.gate.GateCommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GateTeleport extends GateCommandBase {
    public GateTeleport(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin, gate);
    }

    @Override
    public String getName() {
        return "tp";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            Player player = PluginUtil.getPlayer(commandSender);
            if (player != null) {
                return getManager().teleport(strings[1], player);
            }
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/gate tp <gate name>";
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
