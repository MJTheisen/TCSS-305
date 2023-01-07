
package model;

import java.util.ArrayList;
import java.util.Map;

/**from assignment pdf
  A. only travels on streets and through lights and crosswalks.
  B. prefers to drive straight ahead if it can. If it cannot move
     straight ahead, it turns left if possible; if it
     cannot turn left, it turns right if possible; as a last resort, it turns around.
  C. stops for red lights; if a traffic light is immediately ahead of
     the taxi and the light is red, the taxi stays
     still and does not move until the light turns green.
     It does not turn to avoid the light. When the light
     turns green the taxi resumes its original direction.
     Taxis ignore yellow and green lights.
  D. stops for (temporarily) red crosswalk lights. If a crosswalk light is
     immediately ahead of the taxi and
     the crosswalk light is red, the Taxi stays still and does not
     move for 3 clock cycles or until the crosswalk
     light turns green, whichever occurs first. It does not
     turn to avoid the crosswalk light. When the crosswalk light turns green,
     or after 3 time steps, whichever happens first, the taxi resumes its original
     direction. A taxi will drive through yellow or green crosswalk lights without stopping.
 Collision behavior: If it collides with a truck and stays in repair shop for 15 moves.
 */
public class Taxi extends AbstractVehicle {
    private static final int MOVE_CLOCK = 3;
    private static final int DEATH_TIME = 15;
    private int myClockCounter;

    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myClockCounter = 0;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.STREET);
        myTerrain.add(Terrain.LIGHT);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        if ((theNeighbors.get(getDirection()) == Terrain.STREET) || (theNeighbors.get(getDirection()) == Terrain.LIGHT)) {
            myDirection = getDirection();
        } else if ((theNeighbors.get(getDirection().right()) == Terrain.STREET) || (theNeighbors.get(getDirection().right()) == Terrain.LIGHT)) {
            myDirection = getDirection().right();
        } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET) || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
            myDirection = getDirection().left();
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
            } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {
                a = true;
            } else if (theTerrain == Terrain.STREET) {
                a = true;
            } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
                if (myClockCounter == MOVE_CLOCK) {
                    a = true;
                    myClockCounter = 0;
                } else {
                    myClockCounter++;
                }
            }
        }
        return a;
    }
}