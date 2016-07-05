package be.codemonkeys.wim.dirksadventure.domain.rooms;

import be.codemonkeys.wim.dirksadventure.domain.pickupstrategies.FlowerPickUp;
import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.ItemInteraction;
import be.codemonkeys.wim.dirksadventure.domain.Room;

/**
 * Created by Wim on 01/07/2016.
 */
public class RoomFork extends Room {
    public RoomFork(int layout) {
        super(layout);


        putItem(new Item.ItemBuilder(R.id.flower)
                .name("Ipecac flower")
                .defaultItemInteraction(ItemInteraction.PICKUP)
                .pickUpImplementation(new FlowerPickUp())
                .createItem()
        );
    }
}
