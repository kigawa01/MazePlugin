package net.kigawa.mazeplugin.util.plugin.gate.command.gate;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.gate.GateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SetLinked extends GateCommandBase {
    public SetLinked(PluginBase kigawaPlugin, GateManager gate) {
        super(kigawaPlugin, gate);
    }

    @Override
    public String getName() {
        return "setlinked";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 3) {
            return getManager().setLinked(strings[1], strings[2]);
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/gate setlinked <gate name> <linked>";
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
