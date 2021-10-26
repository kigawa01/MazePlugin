package net.kigawa.mazeplugin.util.plugin.inventory;

import net.kigawa.mazeplugin.util.plugin.inventory.button.Button;
import net.kigawa.mazeplugin.util.plugin.inventory.button.NextPage;
import net.kigawa.mazeplugin.util.plugin.inventory.button.PreviousPage;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu extends Storage {
    private int page;
    private Menu next;
    private Menu previous;
    private Button[] buttons;

    public Menu(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType,String name) {
        super(server, inventoryHolder, inventoryType, name);
    }

    public Menu(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType, int page,String name) {
        this(server, inventoryHolder, inventoryType, name);
        this.page = page;
    }

    public Menu(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType, String title,String name) {
        super(server, inventoryHolder, inventoryType, title, name);
    }

    public Menu(Server server, InventoryHolder inventoryHolder, InventoryType inventoryType, String title, int page,String name) {
        this(server, inventoryHolder, inventoryType, title, name);
        this.page = page;
    }

    public Menu(Server server, InventoryHolder inventoryHolder, int column,String name) {
        super(server, inventoryHolder, column, name);
    }

    public Menu(Server server, InventoryHolder inventoryHolder, int column, int page,String name) {
        this(server, inventoryHolder, column, name);
        this.page = page;
    }

    public Menu(Server server, InventoryHolder inventoryHolder, int column, String title,String name) {
        super(server, inventoryHolder, column, title, name);
    }

    public Menu(Server server, InventoryHolder inventoryHolder, int column, String title, int page,String name) {
        this(server, inventoryHolder, column, title,name);
        this.page = page;
    }

    public abstract void onSetup();

    @Override
    public void setup() {
        setSize(getInventory().getSize());
        use(getSize() - 18);
        setDisplayName("");
        setType(Material.BLACK_STAINED_GLASS_PANE);
        set();
        fill(getSize() - 18, getSize() - 10);

        onSetup();
        buttons = new Button[getSize()];
    }

    public void onClick(InventoryClickEvent event) {
        if (getInventory().equals(event.getInventory())) {
            Button button = buttons[event.getSlot()];
            if (button == null) {
                return;
            }

            button.onClick(event);
            if (event.isLeftClick()) {
                button.leftClick(event);
            }
            if (event.isRightClick()) {
                button.rightClick(event);
            }

        }
    }

    public void setNextPage(Menu menu) {
        next.setPrevious(null);
        setNext(menu);
        if (menu == null) {
            return;
        }
        menu.setPrevious(this);
    }

    public void setPreviousPage(Menu menu) {
        previous.setNext(null);
        setPrevious(menu);
        if (menu == null) {
            return;
        }
        menu.setNext(this);
    }

    public void setButton(int index, Button button) {
        buttons[index] = button;
        use(index);
        if (button.getName() != null) {
            setDisplayName(button.getName());
        }
        if (button.getType() != null) {
            setType(button.getType());
        }
        if (button.descriptions() != null) {
            setDescription(button.descriptions());
        }
    }

    public void removeButton(int index) {
        buttons[index] = null;
    }

    public Menu getPrevious() {
        return previous;
    }

    private void setPrevious(Menu previous) {
        this.previous = previous;
        if (previous != null) {
            Button button = new PreviousPage(this);
            setButton(getSize() - 6, button);
        } else {
            removeButton(getSize() - 6);
            use(getSize() - 6);
            setType(Material.AIR);
        }
    }

    public Menu getNext() {
        return next;
    }

    private void setNext(Menu next) {
        this.next = next;
        if (next != null) {
            Button button = new NextPage(this);
            setButton(getSize() - 4, button);
        } else {
            removeButton(getSize() - 4);
            use(getSize() - 4);
            setType(Material.AIR);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
