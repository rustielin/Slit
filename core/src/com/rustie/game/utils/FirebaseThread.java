package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.collision.Ray;
import com.rustie.game.Slit;
import com.rustie.game.sprites.Player;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import box2dLight.Light;
import box2dLight.RayHandler;

/**
 * Created by rustie on 7/25/17.
 */

public class FirebaseThread extends Thread {

    public static final String TAG = "Thread";
    public static final int CYCLE_TIME = 10000;

    private Slit mGame;
    private BeaconSet mBeaconSet; // just a reference to the one we're keeping track of stuff in
    private BeaconSet mTempBeaconSet; // so we know what to send
    private RayHandler mRayHandler;

    public FirebaseThread(Slit game, BeaconSet beaconSet, RayHandler rayHandler) {
        mGame = game;
        mBeaconSet = beaconSet;
        mRayHandler = rayHandler;
        mTempBeaconSet = new BeaconSet();
    }

    /**
     * Periodically send and populate
     */
    @Override
    public void run() {
        super.run();

        while(true) {
            try {
                Thread.sleep(CYCLE_TIME); // sleep for 10 sec
                Gdx.app.log(TAG, "awake now");

                // populate (GET) first, so that we don't get what we just POST
                populateBeaconSet();
                sendBeacons();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Just adds beaconLight to a temp BeaconSet
     * @param beaconLight
     */
    public void sendLater(BeaconLight beaconLight) {
        mTempBeaconSet.add(beaconLight);
    }

    /**
     * Send Firebase the BeaconLights that are in the temp BeaconSet
     * Empties temp BeaconSet
     */
    private void sendBeacons()  {

        final Map<String, Object> data = new HashMap<String, Object>();
        JSONObject json;

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("https://plated-life-108722.firebaseio.com/beacons.json");

        for (final BeaconLight light : mTempBeaconSet) {
            data.put("x", new Float(light.getX()));
            data.put("y", new Float(light.getY()));
            data.put("color", new Float(light.getColor().toFloatBits()));
            data.put("timestamp", new Long(System.currentTimeMillis()));
            data.put("username", mGame.USERNAME); // TODO: do we really need this

            json = new JSONObject(data);

            httpPost.setContent(json.toString());

            Gdx.net.sendHttpRequest(httpPost, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    data.clear();
                    mTempBeaconSet.remove(light);

                }

                @Override
                public void failed(Throwable t) {
                    Gdx.app.log("sendRequest", "http post failed");
                    t.printStackTrace();
                    data.clear();
                    mTempBeaconSet.remove(light);
                }

                @Override
                public void cancelled() {

                }
            });

        }

    }



    /**
     * Populates mBeaconSet by calling HTTP GET on beacons
     * Don't get all of them or something
     */
    private void populateBeaconSet() {

        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);

        // get the last 5 by timestamp
        httpGet.setUrl("https://plated-life-108722.firebaseio.com/beacons.json?orderBy=\"timestamp\"&limitToLast=5");

        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                try {
                    Gdx.app.log("JSON RESPONSE", response);
                    JSONObject json = new JSONObject(response);

                    // make all json entries into lights
                    Iterator<String> keys = json.keys();
                    while (keys.hasNext()) {

                        // light attributes
                        String lightKey = keys.next();
                        JSONObject jsonLight = json.getJSONObject(lightKey);
                        Color lightColor;
                        float x = 0;
                        float y = 0;
                        long timestamp = 0;
                        BeaconLight beacon;

                        // cast the obj into a json
                        try {
                            lightColor = new Color();
                            Color.abgr8888ToColor(lightColor, (float) jsonLight.getDouble("color"));
                            x = (float) jsonLight.getDouble("x");
                            y = (float) jsonLight.getDouble("y");
                            timestamp = jsonLight.getLong("timestamp");
                            if (!mBeaconSet.contains(timestamp)) {
                                float distance = 2; // TODO: Programmatically change this later
                                beacon = new BeaconLight(mRayHandler, 10, lightColor,
                                        distance / 10, x, y, timestamp);
                                mBeaconSet.add(beacon);
                                Gdx.app.log(TAG, "drew Firebase beacon");
                            }


                        } catch (Exception e) {
//                            e.printStackTrace();
                            Gdx.app.log(TAG, "Inner");

                        }
                    }

                } catch (JSONException e) {
//                    e.printStackTrace();
                    Gdx.app.log(TAG, "Outer");

                }
            }

            @Override
            public void failed(Throwable t) {

                Gdx.app.log("populateBeaconSet", "http get failed");
                t.printStackTrace();

            }

            @Override
            public void cancelled() {

            }
        });

    }


}
