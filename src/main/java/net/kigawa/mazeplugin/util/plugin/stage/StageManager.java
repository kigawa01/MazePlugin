package net.kigawa.mazeplugin.util.plugin.stage;

import com.sk89q.worldedit.regions.Region;
import net.kigawa.bordgameplugin.util.all.EqualsNamed;
import net.kigawa.bordgameplugin.util.plugin.all.PluginBase;
import net.kigawa.bordgameplugin.util.plugin.stage.StageData;
import net.kigawa.bordgameplugin.util.plugin.worldedit.world.PlayerRegion;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageManager {
    List<net.kigawa.bordgameplugin.util.plugin.stage.StageData> allStage = new ArrayList<>();
    List<net.kigawa.bordgameplugin.util.plugin.stage.StageData> canUse = new ArrayList<>();
    List<net.kigawa.bordgameplugin.util.plugin.stage.StageData> notUse = new ArrayList<>();
    PluginBase plugin;

    public StageManager(PluginBase kigawaPlugin) {
        plugin = kigawaPlugin;

        File folder = new File(plugin.getDataFolder(), "stage");
        folder.mkdir();
        String[] files = folder.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(folder, files[i]);
            plugin.logger(files[i]);

            net.kigawa.bordgameplugin.util.plugin.stage.StageData data = plugin.getRecorder().load(net.kigawa.bordgameplugin.util.plugin.stage.StageData.class, "stage", files[i].substring(0, files[i].length() - 4));
            canUse.add(data);
            allStage.add(data);
        }
    }

    public void returnStage(net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData) {
        if (notUse.contains(new EqualsNamed(stageData.getName()))) {
            canUse.add(stageData);
            notUse.remove(notUse.indexOf(new EqualsNamed(stageData.getName())));
        }
    }

    public void setStartLoc(String name, int x, int y, int z, CommandSender sender) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = getStage(name, sender);
        if (stageData != null) {
            int[] i = stageData.getStartLoc();
            i[0] = x;
            i[1] = y;
            i[2] = z;
            //logger
            plugin.logger("stage manager startLoc " + stageData.getStartLoc()[0]);
            plugin.logger("length " + stageData.getStartLoc().length);
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public void setStage2(String name, String world, int x, int y, int z, CommandSender sender) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = getStage(name, sender);
        if (stageData != null) {
            stageData.setStageWorld(world);
            double[] i = stageData.getStageLoc();
            i[3] = x;
            i[4] = y;
            i[5] = z;
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public void setStage1(String name, String world, int x, int y, int z, CommandSender sender) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = getStage(name, sender);
        if (stageData != null) {
            stageData.setStageWorld(world);
            double[] i = stageData.getStageLoc();
            i[0] = x;
            i[1] = y;
            i[2] = z;
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public void setStage(String name, Region region, CommandSender sender) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = getStage(name, sender);
        if (stageData != null) {
            PlayerRegion region1 = new PlayerRegion(region);
            stageData.setStageLoc(region1.getCoordinates());
            stageData.setStageWorld(region1.getWorld());
        }
    }

    public net.kigawa.bordgameplugin.util.plugin.stage.StageData getStage(String name, CommandSender sender) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = null;
        if (allStage.contains(new EqualsNamed(name))) {
            stageData = allStage.get(allStage.indexOf(new EqualsNamed(name)));
        } else {
            sender.sendMessage(name + " is not exit");
        }
        return stageData;
    }

    public String setStage(String name) {
        //check can use this name
        if (getStage(name) == null) {
            //create Stage
            net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = new net.kigawa.bordgameplugin.util.plugin.stage.StageData();
            stageData.setName(name);
            //put in list
            canUse.add(stageData);
            allStage.add(stageData);
            //save
            plugin.getRecorder().save(stageData, "stage");
            //send message
            return "create " + name;
        } else {
            return "this name can't use";
        }
    }

    public net.kigawa.bordgameplugin.util.plugin.stage.StageData getStage(String name) {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = null;
        if (name != null) {
            if (allStage.contains(new EqualsNamed(name))) {
                stageData = allStage.get(allStage.indexOf(new EqualsNamed(name)));
            }
        }
        if (name == null) {
            stageData = getRandomStage();
        }
        return stageData;
    }

    public net.kigawa.bordgameplugin.util.plugin.stage.StageData getRandomStage() {
        net.kigawa.bordgameplugin.util.plugin.stage.StageData stageData = null;
        if (canUse.size() > 0) {
            Random random = new Random();
            int randomNumber = random.nextInt(canUse.size());
            stageData = canUse.get(randomNumber);
            notUse.add(stageData);
            canUse.remove(randomNumber);
        }
        return stageData;
    }

    public List<String> getStageNames() {
        List<String> list = new ArrayList<>();
        for (StageData stageData : canUse) {
            list.add(stageData.getName());
        }
        return list;
    }


}
