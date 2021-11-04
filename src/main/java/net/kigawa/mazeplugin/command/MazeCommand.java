package net.kigawa.mazeplugin.command;

import net.kigawa.spigot.pluginutil.PluginBase;
import net.kigawa.spigot.pluginutil.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MazeCommand extends FirstCommand {
    public MazeCommand(PluginBase plugin) {
        super(plugin);
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

    @Override
    public String errorMessage() {
        return "/maze <subcommand>";
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public String getName() {
        return "maze";
    }
}
