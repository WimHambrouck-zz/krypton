package be.codemonkeys.wim.dirksadventure.domain.lookstrategies;

import android.content.Context;

import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.Helper;
import be.codemonkeys.wim.dirksadventure.domain.interfaces.LookStrategy;

/**
 * Created by Wim on 11/07/2016.
 */
public class DefaultLook implements LookStrategy {
    private final int lookMessage;

    public DefaultLook(int lookMessage) {
        this.lookMessage = lookMessage;
    }

    @Override
    public void look(Context context) {
        Helper.sendStatusText(context, context.getString(lookMessage));
    }
}
