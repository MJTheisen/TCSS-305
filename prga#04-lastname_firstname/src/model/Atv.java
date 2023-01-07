
package model;

import java.util.ArrayList;
import java.util.Map;

/** from assignment pdf
 A. can travel on any terrain except walls.
 B. randomly selects to go straight, turn left, or turn right.
 C. never reverses direction (they never need to).
 D. drives through all traffic lights and crosswalk lights without stopping!
 Collision behavior: If it collides with a truck, car, or taxi, and stays in repair shop for 25 moves.
 */
public class Atv extends AbstractVehicle {
    private static final int DEATH_TIME = 25;

    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.STREET);
        myTerrain.add(Terrain.LIGHT);
        myTerrain.add(Terrain.GRASS);
        myTerrain.add(Terrain.TRAIL);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction a = Direction.random();
        if (canPass(Terrain.STREET, Light.GREEN)) {
            while (theNeighbors.get(a) == Terrain.WALL || a == getDirection().reverse()) {
                a = Direction.random();
            }
        } else {
            a = getDirection().reverse();
        }
        return a;
    }

    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean allow = false;
        if (myTerrain.contains(theTerrain)) {
            allow = true;
        }
        return allow;
    }
}