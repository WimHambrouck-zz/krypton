package be.codemonkeys.wim.dirksadventure.domain.pickupstrategies;

import android.content.Context;
import android.widget.Toast;

import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.PickUpStrategy;

/**
 * Created by Wim on 05/07/2016.
 */
public class DefaultPickUp implements PickUpStrategy {
    @Override
    public void pickUp(Context context, Item item) {
        Toast.makeText(context, "You picked up " + item.getName(), Toast.LENGTH_SHORT).show();

    }
}
