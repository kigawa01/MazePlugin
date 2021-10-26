package net.kigawa.mazeplugin.util.yaml;

import net.kigawa.mazeplugin.util.all.Named;

public abstract class YamlData implements Named {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
