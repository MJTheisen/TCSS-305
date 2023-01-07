
package model;

import java.util.ArrayList;
import java.util.Map;

/** from assignment pdf
 A. can travel on streets and through lights and crosswalk lights,
    but they prefer to travel on trails.
 B. If the terrain in front of a bicycle is a trail,
    the bicycle always goes straight ahead in the direction it is facing.
    Trails are guaranteed to be straight (horizontal or vertical)
    lines that end at streets, and a bicycle will never start on a
    trail facing terrain it cannot traverse.
 C. If a bicycle is not facing a trail, but there is a trail either
    to the left or to the right of the bicycle’s current
    direction, then the bicycle turns to face the trail and moves
    in that direction.You may assume that the map is laid out so that
    only one trail will neighbor a bicycle at any given time.
 D. If there is no trail straight ahead, to the left, or to the right,
    the bicycle prefers to move straight ahead
    on a street (or light or crosswalk light) if it can.
    If it cannot move straight ahead, it turns left if possible;
    if it cannot turn left, it turns right if possible. As a last resort,
    if none of these three directions is legal (all not streets or lights
    or crosswalk lights), the bicycle turns around.
 E. ignores green lights.
 F. stops for yellow and red lights; if a traffic light or crosswalk light
    is immediately ahead of the bicycle and the light is not green,
    the bicycle stays still and does not move unless a trail is to the left or right.
    If a bicycle is facing a red or yellow light and there is a trail
    to the left or right, the bicycle will turn to face the trail.
 Collision behavior: If it collides with a truck, car, taxi, or ATV. It stays in repair shop for 35 moves.
 */
public class Bicycle extends AbstractVehicle {
    private static final int DEATH_TIME = 35;

    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.LIGHT);
        myTerrain.add(Terrain.STREET);
        myTerrain.add(Terrain.TRAIL);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = getDirection();
        if (theNeighbors.get(result) == Terrain.STREET || theNeighbors.get(result) == Terrain.LIGHT || theNeighbors.get(result) == Terrain.CROSSWALK) {
            result = moveOnTrail(theNeighbors);
        } else if (theNeighbors.get(result.right()) == Terrain.STREET || theNeighbors.get(result.left()) == Terrain.STREET || theNeighbors.get(result.right()) == Terrain.CROSSWALK || theNeighbors.get(result.left()) == Terrain.CROSSWALK) {
            result = result.left();
        } else if (theNeighbors.get(result) == Terrain.STREET && theNeighbors.get(result) != Terrain.CROSSWALK) {
            result = result.right();
        }
        return result;
    }

    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean a = false;
        if (myTerrain.contains(theTerrain)) {
            a = true;
            if ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) && theLight != Light.GREEN) {
                a = false;
            }
        }
        return a;
    }

    private Direction moveOnTrail(final Map<Direction, Terrain> theNeighbors) {
        Direction result = getDirection();
        if (theNeighbors.get(result.right()) == Terrain.TRAIL) {
            result = result.right();
        } else if (theNeighbors.get(result.left()) == Terrain.TRAIL) {
            result = result.left();
        }
        return result;
    }
}