package be.codemonkeys.wim.dirksadventure.domain;

import android.content.Context;
import android.text.TextUtils;

import be.codemonkeys.wim.dirksadventure.domain.interfaces.LookStrategy;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.PickUpStrategy;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.UseStrategy;

/**
 * Created by Wim on 04/07/2016.
 */
public class Item {

    private int id;
    private String name;
    private PickUpStrategy pickUpImplementation;
    private LookStrategy lookImplementation;
    private UseStrategy useImplementation;
    private ItemInteraction defaultItemInteraction;

    private Item(int id, String name, PickUpStrategy pickUpImplementation, LookStrategy lookImplementation, UseStrategy useImplementation, ItemInteraction defaultItemInteraction) {
        this.id = id;
        this.name = name;
        this.pickUpImplementation = pickUpImplementation;
        this.lookImplementation = lookImplementation;
        this.useImplementation = useImplementation;
        this.defaultItemInteraction = defaultItemInteraction;
    }

    public void pickUp(Context context)
    {
        pickUpImplementation.pickUp(context, this);
        //TODO: play pickup sound
    }

    public void look(Context context)
    {
        lookImplementation.look(context);
    }

    public void use(Context context)
    {
        useImplementation.use(context);
    }

    public void interact(Context context)
    {
        switch (defaultItemInteraction)
        {
            case PICKUP:
                pickUp(context);
                break;
            case LOOK:
                look(context);
                break;
            case USE:
                use(context);
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class ItemBuilder {
        private int id;
        private String name;
        private PickUpStrategy pickUpImplementation;
        private LookStrategy lookImplementation;
        private UseStrategy useImplementation;
        private ItemInteraction defaultItemInteraction;

        public ItemBuilder(int id) {
            this.id = id;
        }

        public ItemBuilder pickUpImplementation(PickUpStrategy pickUpImplementation) {
            this.pickUpImplementation = pickUpImplementation;
            return this;
        }

        public ItemBuilder lookImplementation(LookStrategy lookImplementation) {
            this.lookImplementation = lookImplementation;
            return this;
        }

        public ItemBuilder useImplementation(UseStrategy useImplementation) {
            this.useImplementation = useImplementation;
            return this;
        }

        public ItemBuilder defaultItemInteraction(ItemInteraction defaultItemInteraction) {
            this.defaultItemInteraction = defaultItemInteraction;
            return this;
        }

        public ItemBuilder name(String name)
        {
            this.name = name;
            return this;
        }

        public ItemBuilder id(int id)
        {
            this.id = id;
            return this;
        }

        public Item createItem() {
            return new Item(id, name, pickUpImplementation, lookImplementation, useImplementation, defaultItemInteraction);
        }
    }
}
