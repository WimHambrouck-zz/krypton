package be.codemonkeys.wim.dirksadventure.domain;

import android.content.Context;

import java.util.HashMap;

import be.codemonkeys.wim.dirksadventure.R;
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
    private final String pickUpMessage, lookMessage;

    private Item(int id,
                 int name,
                 PickUpStrategy pickUpImplementation,
                 LookStrategy lookImplementation,
                 UseStrategy useImplementation,
                 ItemInteraction defaultItemInteraction,
                 String pickUpMessage,
                 String lookMessage) {
        this.id = id;
        this.name = name;
        this.pickUpImplementation = pickUpImplementation;
        this.lookImplementation = lookImplementation;
        this.useImplementation = useImplementation;
        this.defaultItemInteraction = defaultItemInteraction;
        this.pickUpMessage = pickUpMessage;
        this.lookMessage = lookMessage;
    }

    /**
     * Invokes the pick up implementation associated with the item
     * @param context The Activity context requesting the interaction
     */
    public void pickUp(Context context)
    {
        pickUpImplementation.pickUp(context, this);
        //TODO: play pickup sound
    }

    /**
     * Invokes the look implementation associated with the item
     * @param context The Activity context requesting the interaction
     */
    public void look(Context context)
    {
        lookImplementation.look(context);
    }

    /**
     * Invokes the use implementation associated with the item
     * @param context The Activity context requesting the interaction
     */
    public void use(Context context)
    {
        useImplementation.use(context);
    }

    /**
     * Performs the default interaction (either Pick up, look or use) on the item
     * @param context The Activity context requesting the interaction
     */
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

    public String getPickUpMessage() {
        return pickUpMessage;
    }

    public String getLookMessage() {
        return lookMessage;
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
        private String pickUpMessage, lookMessage;

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

        public ItemBuilder pickUpMessage(String pickUpMessage) {
            this.pickUpMessage = pickUpMessage;
            return this;
        }

        public ItemBuilder lookMessage(String lookMessage) {
            this.lookMessage = lookMessage;
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
                lookImplementation = new DefaultLook(R.string.default_look);
            }

            if(defaultItemInteraction == null)
            {
                throw new IllegalArgumentException("No default interaction specified!");
            }


            return new Item(
                    id,
                    name,
                    pickUpImplementation,
                    lookImplementation,
                    useImplementation,
                    defaultItemInteraction,
                    pickUpMessage,
                    lookMessage
            );
        }
    }
}
