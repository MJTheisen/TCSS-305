package model;

import java.util.ArrayList;
import java.util.Map;

/**from assignment pdf
  A. moves in a random direction (straight, left, or right), always on grass or crosswalks.
  B. never reverses its direction unless there is no other option.
  C. will not turn to avoid the crosswalk.
  D. If a human is next to a crosswalk, it will always choose to turn to face
     in the crosswalk direction. (You can assume that the map of terrain will
     never contain crosswalks that are so close together that a human
     might be adjacent to more than one at the same time.)
  E. does not travel through crosswalks when the crosswalk light is green. If a human is facing a green
     crosswalk, it will wait until the light changes to yellow and then cross through the crosswalk.
  F. Humans travel through crosswalks when the crosswalk light is yellow or red.
     (Note that C and D are complementary sentences, so as E and F.)
  Collision behavior: gets hurt if s/he is hit by any vehicle (other than a human,)
     and stays in hospital for 45 time steps.
 */
public class Human extends AbstractVehicle {
    private static final int DEATH_TIME = 45;

    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = DEATH_TIME;
        myTerrain = new ArrayList<Terrain>();
        myTerrain.add(Terrain.CROSSWALK);
        myTerrain.add(Terrain.GRASS);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = Direction.random();
        result = moveOnCrosswalk(theNeighbors);
        if (canPass(Terrain.GRASS, Light.YELLOW)) {
            if (reverseCheck(theNeighbors)) {
                result = getDirection().reverse();
            } else {
                while (theNeighbors.get(result) != Terrain.GRASS && theNeighbors.get(result) != Terrain.CROSSWALK || result == getDirection().reverse()) {
                    result = Direction.random();
                }
            }
        }
        return result;
    }

    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean a = false;
        if (myTerrain.contains(theTerrain)) {
            a = true;
            if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
                a = false;
            }
        }
        return a;
    }

    private Direction moveOnCrosswalk(final Map<Direction, Terrain> theNeighbors) {
        final Direction result = getDirection();
        Direction rand = Direction.random();
        if (theNeighbors.get(result.right()) == Terrain.CROSSWALK) {
            rand = result.right();
        } else if (theNeighbors.get(result.left()) == Terrain.CROSSWALK) {
            rand = result.left();
        } else if (theNeighbors.get(result) == Terrain.CROSSWALK) {
            rand = getDirection();
        }
        return rand;
    }

    private boolean reverseCheck(final Map<Direction, Terrain> theNeighbors) {
        final Direction result = getDirection();
        return theNeighbors.get(getDirection()) != Terrain.GRASS && theNeighbors.get(result.right()) != Terrain.GRASS && theNeighbors.get(result.left()) != Terrain.GRASS && theNeighbors.get(result) != Terrain.CROSSWALK && theNeighbors.get(result.right()) != Terrain.CROSSWALK && theNeighbors.get(result.left()) != Terrain.CROSSWALK;
    }
}