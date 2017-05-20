package com.rustie.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by rustie on 5/19/17.
 */

public class Wall extends InteractiveTileObject {
    public Wall(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
