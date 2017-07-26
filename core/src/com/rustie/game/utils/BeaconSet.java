package com.rustie.game.utils;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by rustie on 7/25/17.
 *
 * Set of BeaconLights for easy compare with timestamps
 */

public class BeaconSet implements Iterable<BeaconLight> {

    HashSet<BeaconLight> beaconLights;
    long minTimestamp = Long.MAX_VALUE;

    public BeaconSet() {
        beaconLights = new HashSet<BeaconLight>();
    }

    /**
     * Basically same as other contains but worse since you have to make a BeaconLight
     * to pass into parameters
     * @param beaconLight
     * @return
     */
    public boolean contains(BeaconLight beaconLight) {
        return beaconLights.contains(beaconLight);
    }

    /**
     * Check if beaconLights already has a light with the same timestamp
     * @param timestamp
     * @return
     */
    public boolean contains(long timestamp) {
        for (BeaconLight light : beaconLights) {
            if (light.getTimestamp() == timestamp) {
                return true;
            }
        }
        return false;
    }

    public void add(BeaconLight beaconLight) {
        // there's a new min
        if (beaconLight.getTimestamp() < minTimestamp) {
            minTimestamp = beaconLight.getTimestamp();
        }
        beaconLights.add(beaconLight);
    }

    /**
     * Uses the get method to get the BeaconLight with the timestamp
     * @param timestamp
     */
    public void remove(long timestamp) {
        beaconLights.remove(get(timestamp));

        // gotta find a new min
        if (minTimestamp == timestamp) {
            minTimestamp = getNewMinTimestamp();
        }
    }

    /**
     * Remove the given beaconLight
     * @param beaconLight
     */
    public void remove(BeaconLight beaconLight) {
        beaconLights.remove(beaconLight);
    }

    /**
     * gets the BeaconLight with the timestamp
     * Null if we can't find it
     * @param timestamp
     * @return
     */
    public BeaconLight get(long timestamp) {
        for (BeaconLight light : beaconLights) {
            if (light.getTimestamp() == timestamp) {
                return light;
            }
        }
        return null;
    }

    /**
     * Programmatically get min
     * @return
     */
    private BeaconLight getMinBeaconLight() {
        BeaconLight min = null;
        for (BeaconLight beaconLight : beaconLights) {
            if (min == null || beaconLight.getTimestamp() < min.getTimestamp()) {
                min = beaconLight;
            }
        }
        return min;
    }

    /**
     * Used to calculate the new min timestamp after a removal
     * @return
     */
    private long getNewMinTimestamp() {
        return getMinBeaconLight().getTimestamp();
    }

    public int size() {
        return beaconLights.size();
    }

    /**
     * Removes the min BeaconLight and calculates the new min
     */
    public void removeMin() {
        remove(minTimestamp);
        minTimestamp = getNewMinTimestamp();
    }

    @Override
    public Iterator<BeaconLight> iterator() {
        return beaconLights.iterator();
    }
}
