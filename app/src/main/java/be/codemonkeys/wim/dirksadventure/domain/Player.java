package be.codemonkeys.wim.dirksadventure.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wim on 05/07/2016.
 */
public class Player {
    private String name;
    private List<Item> inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToInventory(Item item)
    {
        inventory.add(item);
    }

    public void removeFromInventory(Item item)
    {
        inventory.remove(item);
    }
}
