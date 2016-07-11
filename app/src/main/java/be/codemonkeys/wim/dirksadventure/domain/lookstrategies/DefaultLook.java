package be.codemonkeys.wim.dirksadventure.domain.lookstrategies;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import be.codemonkeys.wim.dirksadventure.RoomActivity;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.LookStrategy;

/**
 * Created by Wim on 11/07/2016.
 */
public class DefaultLook implements LookStrategy {
    @Override
    public void look(Context context) {
        Intent intent = new Intent("msgtest");
        // You can also include some extra data.
        intent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
