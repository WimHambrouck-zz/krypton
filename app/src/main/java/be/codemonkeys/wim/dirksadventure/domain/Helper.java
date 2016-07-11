package be.codemonkeys.wim.dirksadventure.domain;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Wim on 12/07/2016.
 */
public final class Helper {

    public static final String INTENT_FILTER = "setStatusText";
    public static final String INTENT_EXTRA = "message";

    /**
     * Prevent instantiation of class with private constructor
     */
    private Helper() {}

    public static void sendStatusText(Context context, String message)
    {
        Intent intent = new Intent(INTENT_FILTER);
        // You can also include some extra data.
        intent.putExtra(INTENT_EXTRA, message);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
