package be.codemonkeys.wim.dirksadventure.domain.rooms;

import be.codemonkeys.wim.dirksadventure.domain.lookstrategies.DefaultLook;
import be.codemonkeys.wim.dirksadventure.domain.pickupstrategies.CannotPickUp;
import be.codemonkeys.wim.dirksadventure.domain.pickupstrategies.DefaultPickUp;
import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.ItemInteraction;
import be.codemonkeys.wim.dirksadventure.domain.Room;

/**
 * Created by Wim on 01/07/2016.
 */
public class RoomFork extends Room {
    public RoomFork(int layout, int name) {
        super(layout, name);


        putItem(new Item.ItemBuilder(R.id.flower)
                .name(R.string.flower_name)
                .defaultItemInteraction(ItemInteraction.PICKUP)
                .pickUpImplementation(new DefaultPickUp(R.string.flower_pickup))
                .createItem()
        );

        putItem(new Item.ItemBuilder(R.id.signpost)
                .name(R.string.signpost_name)
                .defaultItemInteraction(ItemInteraction.LOOK)
                .pickUpImplementation(new CannotPickUp())
                .lookImplementation(new DefaultLook(R.string.signpost_look))
                .createItem()
        );
    }
}
