package net.kigawa.mazeplugin.util.plugin.game;

import net.kigawa.mazeplugin.util.plugin.all.PluginBase;
import net.kigawa.mazeplugin.util.plugin.all.message.sender.ErrorSender;

import java.util.ArrayList;
import java.util.List;

public abstract class GameManagerBase<D extends GameDataBase, G extends GameBase> {
    private final List<D> dataList;
    private final List<G> gameList = new ArrayList<>();
    private final PluginBase plugin;

    public GameManagerBase(PluginBase plugin) {
        dataList = plugin.getRecorder().loadAll(getDataClass(), getName());
        this.plugin = plugin;
    }

    public abstract String getName();

    public abstract D newData();

    public abstract G newGame(D data);

    public abstract Class<D> getDataClass();

    public abstract Class<G> getGameClass();

    public String create(String name, String world) {
        D data = getData(name);
        if (data == null) return name + " is exit";
        data = newData();
        data.setName(name);
        data.setWorld(world);
        getDataList().add(data);
        return name + " is created";
    }

    public String start(String name) {
        G game = getGame(name);
        if (game != null) {
            return ErrorSender.getString(name + "is already started");
        }
        D data = getData(name);
        if (data == null) {
            return ErrorSender.getString(name + "is not exit");
        }
        game = newGame(data);
        gameList.add(game);
        return game.start();
    }

    public D getData(String name) {
        for (D data : dataList) {
            if (data.getName().equals(name)) {
                return data;
            }
        }
        return null;
    }

    public G getGame(String name) {
        for (G gameBase : gameList) {
            if (gameBase.equals(name)) {
                return gameBase;
            }
        }
        return null;
    }

    public List<G> getGameList() {
        return gameList;
    }

    public List<D> getDataList() {
        return dataList;
    }

    public PluginBase getPlugin() {
        return plugin;
    }
}
