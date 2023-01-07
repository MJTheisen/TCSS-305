package model;

import java.util.ArrayList;
import java.util.Map;

/**from assignment pdf
  A. travels only on streets and through lights and crosswalks.
  B. randomly selects to go straight, turn left, or turn right.
     As a last resort, if none of these three directions
     is legal (all not streets, lights, or crosswalks), it turns around.
  C. drives through all traffic lights without stopping!
  D. stops for red crosswalk lights but drive through yellow or green
     crosswalk lights without stopping.
  Collision behavior: survives a collision with any other vehicle
 */
public class Truck extends AbstractVehicle {
    private static final int DEATH_TIME = 0;

    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.STREET);
        myTerrain.add(Terrain.LIGHT);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = Direction.random();
        if (canPass(Terrain.STREET, Light.GREEN)) {
            if (truckReverse(theNeighbors)) {
                resultDirection = getDirection().reverse();
            } else {
                while (theNeighbors.get(resultDirection) != Terrain.STREET && theNeighbors.get(resultDirection) != Terrain.CROSSWALK && theNeighbors.get(resultDirection) != Terrain.LIGHT || resultDirection == getDirection().reverse()) {
                    resultDirection = Direction.random();
                }
            }
        }
        return resultDirection;
    }

    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean allow = false;
        if (myTerrain.contains(theTerrain)) {
            if (theTerrain.equals(Terrain.CROSSWALK) && (theLight == Light.RED)) {
                allow = false;
            } else {
                allow = true;
            }
        }
        return allow;
    }

    private boolean truckReverse(final Map<Direction, Terrain> theNeighbors) {
        final Direction currentDirection = getDirection();
        return theNeighbors.get(getDirection()) != Terrain.STREET && theNeighbors.get(currentDirection) != Terrain.LIGHT && theNeighbors.get(currentDirection.right()) != Terrain.LIGHT && theNeighbors.get(currentDirection.left()) != Terrain.LIGHT && theNeighbors.get(currentDirection) != Terrain.CROSSWALK && theNeighbors.get(currentDirection.right()) != Terrain.CROSSWALK && theNeighbors.get(currentDirection.left()) != Terrain.CROSSWALK && (theNeighbors.get(currentDirection.right()) != Terrain.STREET) && (theNeighbors.get(currentDirection.left()) != Terrain.STREET);
    }

}