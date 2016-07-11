package be.codemonkeys.wim.dirksadventure.domain;

import java.util.HashMap;

public abstract class Room {
    private final int layout;
    private final int name;
    private HashMap<Direction, Room> adjacentRooms;
    private HashMap<Integer, Item> items;

    /**
     * Adds or updates a room associated with a direction. Also updates the inverse direction in
     * the other room (e.g: when updating room X's UP with room Y, room Y's DOWN will automatically
     * be updated to point to room X)
     *
     * @param direction The Direction to update
     * @param room      The Room this direction leads to
     */
    public void putAdjacentRoom(Direction direction, Room room) {
        this.adjacentRooms.put(direction, room);

        Direction inverseDirection = null;

        switch (direction) {
            case UP:
                inverseDirection = Direction.DOWN;
                break;
            case RIGHT:
                inverseDirection = Direction.LEFT;
                break;
            case DOWN:
                inverseDirection = Direction.UP;
                break;
            case LEFT:
                inverseDirection = Direction.RIGHT;
                break;
        }

        room.getAdjacentRooms().put(inverseDirection, this);
    }

    public void putItem(Item item)
    {
        items.put(item.getId(), item);
    }

    public Item getItem(int id)
    {
        return items.get(id);
    }

    public Room(int layout, int name) {
        this.layout = layout;
        this.name = name;
        this.adjacentRooms = new HashMap<>();
        this.items = new HashMap<>();

        //create an entry in the adjacentRooms map for every possible direction
        for (Direction d : Direction.values()) {
            this.adjacentRooms.put(d, null);
        }

    }

    public int getLayout() {
        return layout;
    }

    public HashMap<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public int getName() {
        return name;
    }
}
