package net.kigawa.mazeplugin.util.plugin.inventory;

import com.google.common.collect.Lists;
import net.kigawa.mazeplugin.util.plugin.player.User;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Inventory inventory;
    private int size;
    private int workIndex;
    private ItemStack workItemStack;
    private ItemMeta workItemMeta;
    private final String name;

    public Storage(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType, String name) {
        inventory = server.createInventory(inventoryHolder, inventoryType);
        setup();
        this.name = name;
    }

    public Storage(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType, String title, String name) {
        inventory = server.createInventory(inventoryHolder, inventoryType, title);
        setup();
        this.name = name;
    }

    public Storage(Server server, InventoryHolder inventoryHolder, int column, String name) {
        inventory = server.createInventory(inventoryHolder, column * 9);
        setup();
        this.name = name;
    }

    public Storage(Server server, InventoryHolder inventoryHolder, int column, String title, String name) {
        inventory = server.createInventory(inventoryHolder, column * 9, title);
        setup();
        this.name = name;
    }

    public void setup() {
        size = inventory.getSize();
    }

    public InventoryView open(User user) {
        return open(user.getPlayer());
    }

    public InventoryView open(HumanEntity entity) {
        return entity.openInventory(inventory);
    }

    public boolean setDescription(int index, String[] descriptions) {
        if (checkIndex(index)) {
            getItemMeta(index);
            setDescription(descriptions);
            setItemMeta();
            return true;
        }
        return false;
    }

    public void setDescription(String[] descriptions) {
        List<String> lore = new ArrayList<>();
        Lists.asList(lore, descriptions);
        workItemMeta.setLore(lore);
    }

    public boolean setDisplayName(int index, String name) {
        if (checkIndex(index)) {
            getItemMeta(index);
            setDisplayName(name);
            setItemMeta();
            return true;
        }
        return false;
    }

    public void setDisplayName(String name) {
        workItemMeta.setDisplayName(name);
    }

    public boolean setType(int index, Material material) {
        if (checkIndex(index)) {
            getItemStack(index);
            setType(material);
            setItemStack();
            return true;
        }
        return false;
    }

    public void setType(Material material) {
        workItemStack.setType(material);
    }

    public boolean fillType(int minIndex, int maxIndex, Material material) {
        for (int i = minIndex; i <= maxIndex; i++) {
            if (setType(i, material)) {
                return false;
            }
        }
        return true;
    }

    public boolean setAmount(int index, int amount) {
        if (checkIndex(index)) {
            getItemStack(index);
            workItemStack.setAmount(amount);
            setItemStack();
            return true;
        }
        return false;
    }

    public boolean use(int index) {
        if (checkIndex(index)) {
            getItemMeta(index);
            return true;
        }
        return false;
    }

    public void set() {
        setItemMeta();
    }

    public boolean fill(int minIndex, int maxIndex) {
        return fill(minIndex, maxIndex, workItemStack);
    }

    public boolean fill(int minIndex, int maxIndex, ItemStack itemStack) {
        for (int i = minIndex; i <= maxIndex; i++) {
            if (setItemStack(i, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public ItemMeta getItemMeta(int index) {
        getItemStack(index);
        workItemMeta = workItemStack.getItemMeta();
        return workItemMeta;
    }

    public void setItemMeta(int index, ItemMeta itemMeta) {
        workItemStack.setItemMeta(itemMeta);
        setItemStack(index, workItemStack);
    }

    public void setItemMeta(ItemMeta itemMeta) {
        setItemMeta(workIndex, itemMeta);
    }

    public void setItemMeta() {
        setItemMeta(workItemMeta);
    }

    public ItemStack getItemStack(int index) {
        this.workIndex = index;
        this.workItemStack = inventory.getItem(index);
        return workItemStack;
    }

    public boolean setItemStack(int index, ItemStack itemStack) {
        if (checkIndex(index)) {
            inventory.setItem(index, itemStack);
            return true;
        }
        return false;
    }

    public void setItemStack(ItemStack itemStack) {
        setItemStack(workIndex, itemStack);
    }

    public void setItemStack() {
        setItemStack(workItemStack);
    }

    public boolean checkIndex(int index) {
        return index <= size && index >= 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Inventory getInventory() {
        return inventory;
    }
}