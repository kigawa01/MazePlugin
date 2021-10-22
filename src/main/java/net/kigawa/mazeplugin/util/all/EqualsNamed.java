package net.kigawa.mazeplugin.util.all;

import net.kigawa.bordgameplugin.util.all.Named;

public class EqualsNamed {
    String name;

    public EqualsNamed(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (o!=null) {

            return ((Named) o).getName().equals(name);
        }
        return false;
    }
}
