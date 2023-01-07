package model;

import java.util.ArrayList;
import java.util.Map;

/**
 This is the abstract class for all the different vehicles.
 it also implements the Vehicle interface
 */

abstract class AbstractVehicle implements Vehicle {
    //instances fields
    protected boolean myIsAlive;
    protected int myPokes;
    protected Direction myDirection;
    protected int myX;
    protected int myY;
    protected int myStarterX;
    protected int myStarterY;
    protected int myDeathTime;
    protected Direction myOriginalDirection;
    protected ArrayList<Terrain> myTerrain;

    protected AbstractVehicle(int theX, int theY, Direction theDir) {
        myDirection = theDir;
        setY(theY);
        setX(theX);

        myStarterX = theX;
        myStarterY = theY;
        myOriginalDirection = theDir;
        myIsAlive = true;
    }

    // for the direction of the vehicle
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    // whether the vehicle can move on the terrain or not
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    // whether the vehicle is dead or alive
    @Override
    public void collide(Vehicle theOther) {
        if (this.getDeathTime() > theOther.getDeathTime()) {
            this.myIsAlive = false;
        } else {
            this.myIsAlive = true;
        }
    }

    // gets the image by using a string
    @Override
    public String getImageFileName() {
        final StringBuilder a = new StringBuilder();
        a.append(getClass().getSimpleName().toLowerCase());
        if (myIsAlive) {
            a.append(".gif");
        } else {
            a.append("_dead.gif");
        }
        return a.toString();
    }

    //returns the number of updates for death and revive
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    //returns direction facing
    @Override
    public Direction getDirection() {
        return myDirection;
    }

    //x coordinate of vehicle
    @Override
    public int getX() {
        return myX;
    }

    //y coordinate of vehicle
    @Override
    public int getY() {
        return myY;
    }

    //returns alive status
    @Override
    public boolean isAlive() {
        return myIsAlive;
    }

    //amount of time dead vehicles are poked
    @Override
    public void poke() {
        if (!myIsAlive) {
            if (myPokes == myDeathTime) {
                myIsAlive = true;
                myPokes = 0;
                setDirection(Direction.random());
            } else {
                myPokes++;
            }
        }
    }

    //string for vehicle in debug mode
    public String toString() {
        final StringBuilder a = new StringBuilder();
        a.append(myIsAlive);
        a.append(" : Pokes: " + myPokes);
        a.append(" Position: [x=" + myX + ", y=" + myY + "]");
        return a.toString();
    }

    //return to initial state
    @Override
    public void reset() {
        setX(myStarterX);
        setY(myStarterY);
        setDirection(myOriginalDirection);
        myIsAlive = true;
    }

    //sets movement direction
    @Override
    public void setDirection(Direction theDir) {
        myDirection = theDir;
    }

    //set x of vehicle
    @Override
    public void setX(int theX) {
        myX = theX;
    }

    //set y of vehicle
    @Override
    public void setY(int theY) {
        myY = theY;
    }

}