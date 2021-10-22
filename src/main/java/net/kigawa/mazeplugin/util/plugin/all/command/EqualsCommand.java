package net.kigawa.mazeplugin.util.plugin.all.command;

import net.kigawa.bordgameplugin.util.plugin.all.command.Command;

public class EqualsCommand {
    String command;
    public EqualsCommand(String command){
        this.command=command;
    }
    @Override
    public  boolean equals(Object o){
        return  ((Command)o).getName().equals(command);
    }
}
