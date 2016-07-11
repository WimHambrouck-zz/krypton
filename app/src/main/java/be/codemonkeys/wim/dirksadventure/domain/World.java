package be.codemonkeys.wim.dirksadventure.domain;

import be.codemonkeys.wim.dirksadventure.R;
import be.codemonkeys.wim.dirksadventure.domain.rooms.RoomFork;
import be.codemonkeys.wim.dirksadventure.domain.rooms.RoomSeaside;
import be.codemonkeys.wim.dirksadventure.domain.rooms.RoomTownCentre;

/**
 * Creates all the rooms and sets their directional relations. Also contains the current room the
 * player is in.
 */
public class World {

    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public World() {
        Room townCentre = new RoomTownCentre(R.layout.room_town_centre, R.string.town_centre);
        Room fork = new RoomFork(R.layout.room_fork, R.string.fork);
        Room seaside = new RoomSeaside(R.layout.room_seaside, R.string.seaside);

        townCentre.putAdjacentRoom(Direction.UP, fork);
        townCentre.putAdjacentRoom(Direction.DOWN, seaside);

        currentRoom = townCentre;
    }
}
