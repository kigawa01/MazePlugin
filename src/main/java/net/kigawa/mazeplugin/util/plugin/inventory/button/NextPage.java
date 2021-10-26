package net.kigawa.mazeplugin.util.plugin.inventory.button;

import net.kigawa.mazeplugin.util.plugin.inventory.Menu;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NextPage extends Button {
    private Menu menu;

    public NextPage(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Material getType() {
        return Material.ARROW;
    }

    @Override
    public String getName() {
        return "次のページ";
    }

    @Override
    public String[] descriptions() {
        return new String[0];
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        menu.getNext().open(event.getWhoClicked());
    }

    @Override
    public void leftClick(InventoryClickEvent event) {

    }

    @Override
    public void rightClick(InventoryClickEvent event) {

    }
}
