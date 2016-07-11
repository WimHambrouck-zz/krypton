package be.codemonkeys.wim.dirksadventure.domain.pickupstrategies;

import android.content.Context;

import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.Helper;
import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.PickUpStrategy;

/**
 * Used when an item can't be picked up.
 */
public class CantPickUp implements PickUpStrategy {
    /**
     * Displays the items pick up message or a default error messages if it is not present
     * @param context The Activity context requesting the interaction
     * @param item The item being picked up
     */
    @Override
    public void pickUp(Context context, Item item) {
        Helper.sendStatusText(context, context.getString(R.string.cant_pickup));
    }
}
