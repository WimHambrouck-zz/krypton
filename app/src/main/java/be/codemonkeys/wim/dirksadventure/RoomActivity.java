package be.codemonkeys.wim.dirksadventure;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import be.codemonkeys.wim.dirksadventure.domain.Direction;
import be.codemonkeys.wim.dirksadventure.domain.Item;
import be.codemonkeys.wim.dirksadventure.domain.Player;
import be.codemonkeys.wim.dirksadventure.domain.Room;
import be.codemonkeys.wim.dirksadventure.domain.World;

public class RoomActivity extends AppCompatActivity  {

    private GestureDetectorCompat gDetector;
    private World world;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: 30/06/2016 Load functie

        this.world = new World();
        changeRoom(this.world.getCurrentRoom());
        TextView text = (TextView) findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MorePerfectDOSVGA.ttf");
        text.setTypeface(tf);

        //Instantiate the gesture detector
        gDetector = new GestureDetectorCompat(this, new GestureListener(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //send the touch event to the GestureDetector
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * Removes the action bar and makes the background (the room) full screen
     */
    private void makeMeFullscreen() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        View background = findViewById(R.id.background);

        /**
         * Note: Some of these flags are not supported by API 14, but will simply be ignored
         * by the device, without raising an exception.
         * I've included them nonetheless, because if the app is running on a device with API >14,
         * they will work.
         */
        background.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    /**
     * To change rooms. Sets the background to the layout of the new room, makes it fullscreen and
     * updates the currentRoom in the World.
     * @param newRoom The room we're going to
     */
    private void changeRoom(Room newRoom) {
        setContentView(newRoom.getLayout());
        makeMeFullscreen();
        world.setCurrentRoom(newRoom);
    }

    public void itemClicked(View view)
    {
        Item clickedItem = world.getCurrentRoom().getItem(view.getId());

        clickedItem.interact(this);

//        int id = view.getId();
//
//        if(id == R.id.flower)
//        {
//            Toast.makeText(this, "it's a flower!", Toast.LENGTH_SHORT).show();
//            view.setVisibility(View.INVISIBLE);
//        }
    }

    /**
     * GestureListener for RoomActivity. Handles the onDown and onFling gestures
     */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private final Context _context;

        public GestureListener(Context context) {
            _context = context;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
        /*
        From the android documentation:

        Whether or not you use GestureDetector.OnGestureListener,
        it's best practice to implement an onDown() method that returns true.
        This is because all gestures begin with an onDown() message.
        If you return false from onDown(), as GestureDetector.SimpleOnGestureListener does by default,
        the system assumes that you want to ignore the rest of the gesture,
        and the other methods of GestureDetector.OnGestureListener never get called.
        This has the potential to cause unexpected problems in your app.
        The only time you should return false from onDown() is if you truly want to ignore an entire gesture.
         */
            return true;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //get the direction of the event
            Direction direction = getFlingDirection(e1.getX(), e1.getY(), e2.getX(), e2.getY());

            //get the adjacent room associated with the direction
            Room newRoom = world.getCurrentRoom().getAdjacentRooms().get(direction);

            if(newRoom != null)
            {
                //if the room exists, change to the new room
                changeRoom(newRoom);
            } else {
                //TODO: what if no room?
                Toast.makeText(_context, "Dat gaat toch helemaal niet, joh", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        /**
         * Returns the direction of the 'fling' (== swipe) action based on it's angle.
         * based on: https://vshivam.wordpress.com/2014/04/28/detecting-up-down-left-right-swipe-on-android/
         * @param x1 X parameter of the first MotionEvent
         * @param y1 Y parameter of the first MotionEvent
         * @param x2 X parameter of the second MotionEvent
         * @param y2 Y parameter of the second MotionEvent
         * @return The correct Direction
         */
        private Direction getFlingDirection(float x1, float y1, float x2, float y2) {
            Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));

            if (angle > 45 && angle <= 135)
                return Direction.UP;
            if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
                return Direction.LEFT;
            if (angle < -45 && angle>= -135)
                return Direction.DOWN;
            if (angle > -45 && angle <= 45)
                return Direction.RIGHT;

            return null;
        }
    }
}


