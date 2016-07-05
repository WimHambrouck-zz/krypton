package be.codemonkeys.wim.dirksadventure.domain.interfaces;

import android.content.Context;

import be.codemonkeys.wim.dirksadventure.domain.Item;

/**
 * Created by Wim on 04/07/2016.
 */
public interface PickUpStrategy {
    void pickUp(Context context, Item item);
}
