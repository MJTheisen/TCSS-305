package model;

import java.util.ArrayList;
import java.util.Map;

/**from assignment pdf
  A. can only travel on streets and through lights and crosswalks.
  B. prefers to drive straight ahead on the street if it can.
     If it cannot move straight ahead, it turns left if
     possible; if it cannot turn left, it turns right if possible;
     as a last resort, it turns around.
  C. stops for red lights; if a traffic light is immediately ahead
     of the car and the light is red, the car stays
     still and does not move. It does not turn to avoid the light.
     When the light turns green, the car resumes its original direction.
  D. ignores yellow and green lights.
  E. stops for red and yellow crosswalk lights but
     drive through green crosswalk lights without stopping
  Collision behavior: If it collides with truck and stays in repair shop for 15 moves.
 */
public class Car extends AbstractVehicle {
    private static final int DEATH_TIME = 15;

    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.STREET);
        myTerrain.add(Terrain.LIGHT);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        if ((theNeighbors.get(getDirection()) == Terrain.STREET) || (theNeighbors.get(getDirection()) == Terrain.LIGHT)) {
            myDirection = getDirection();
        } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET) || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
            myDirection = getDirection().left();
        } else if ((theNeighbors.get(getDirection().right()) == Terrain.STREET) || (theNeighbors.get(getDirection().right()) == Terrain.LIGHT)) {
            myDirection = getDirection().right();
        } else {
            myDirection = getDirection().reverse();
        }
        return myDirection;
    }

    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean a = false;
        if (myTerrain.contains(theTerrain)) {
            if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
                a = true;
            } else if (theTerrain == Terrain.LIGHT && (theLight == Light.GREEN || theLight == Light.YELLOW)) {
                a = true;
            } else if (theTerrain == Terrain.STREET) {
                a = true;
            }
        }
        return a;
    }
}