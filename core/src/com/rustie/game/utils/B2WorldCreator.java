package com.rustie.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.rustie.game.sprites.Coin;
import com.rustie.game.sprites.Wall;

import box2dLight.RayHandler;

/**
 * Created by rustie on 5/19/17.
 */

public class B2WorldCreator {
    private Integer coins;

    public B2WorldCreator(World world, TiledMap tiledMap, RayHandler rayHandler) {

        coins = 0;

        // for each wall (by index in Tiled)
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Wall(world, tiledMap, rect, rayHandler);

        }

        // for each coin (by index in Tiled)
        for (MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(world, tiledMap, rect, rayHandler);
        }
    }

}
