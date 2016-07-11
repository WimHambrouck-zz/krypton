package be.codemonkeys.wim.dirksadventure.domain.pickupstrategies;

import android.content.Context;
import android.widget.Toast;

import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.Helper;
import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.PickUpStrategy;

/**
 * Created by Wim on 05/07/2016.
 */
public class DefaultPickUp implements PickUpStrategy {

    private final Integer pickUpMessage;

    public DefaultPickUp()
    {
        this.pickUpMessage = null;
    }

    public DefaultPickUp(int pickUpMessage)
    {
        this.pickUpMessage = pickUpMessage;
    }

    @Override
    public void pickUp(Context context, Item item) {
        if(pickUpMessage == null)
        {
            Helper.sendStatusText(context, String.format(context.getString(R.string.default_pickup), context.getString(item.getName())));
        } else {
            Helper.sendStatusText(context, context.getString(pickUpMessage));
        }
    }
}
