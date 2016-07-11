package be.codemonkeys.wim.dirksadventure.domain;

import android.content.Context;

import be.codemonkeys.wim.dirksadventure.domain.interfaces.LookStrategy;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.PickUpStrategy;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.UseStrategy;
import be.codemonkeys.wim.dirksadventure.domain.lookstrategies.DefaultLook;
import be.codemonkeys.wim.dirksadventure.domain.pickupstrategies.DefaultPickUp;

/**
 * Created by Wim on 04/07/2016.
 */
public class Item {

    private int id;
    private int name;
    private PickUpStrategy pickUpImplementation;
    private LookStrategy lookImplementation;
    private UseStrategy useImplementation;
    private ItemInteraction defaultItemInteraction;

    private Item(int id, int name, PickUpStrategy pickUpImplementation, LookStrategy lookImplementation, UseStrategy useImplementation, ItemInteraction defaultItemInteraction) {
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

    public int getName() {
        return name;
    }

    /**
     * Builder class for new items.
     */
    public static class ItemBuilder {
        private int id;
        private int name;
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

        public ItemBuilder name(int name)
        {
            this.name = name;
            return this;
        }

        public ItemBuilder id(int id)
        {
            this.id = id;
            return this;
        }

        /**
         * Creates a new item with the values set on the builder. Note: if any strategy is not set,
         * the default strategy will be set. (e.g.: DefaultPickUp if nu pickUpImplementation is set)
         * @return an instantiation of Item with the values set on the builder
         */
        public Item createItem() {
            if(pickUpImplementation == null)
            {
                pickUpImplementation = new DefaultPickUp();
            }

            if(lookImplementation == null)
            {
                lookImplementation = new DefaultLook();
            }

            if(defaultItemInteraction == null)
            {
                throw new IllegalArgumentException("No default interaction specified!");
            }


            return new Item(id, name, pickUpImplementation, lookImplementation, useImplementation, defaultItemInteraction);
        }
    }
}
