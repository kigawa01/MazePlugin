package net.kigawa.mazeplugin.util.plugin.all.recorder;

import net.kigawa.mazeplugin.util.yaml.YamlData;

public class RecorderData extends YamlData {
    String name;

    public RecorderData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
