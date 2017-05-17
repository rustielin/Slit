package com.rustie.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rustie on 5/17/17.
 */

public class Wave extends PxSprite {

    private Vector3 mPosition;
    private int speedScale; // controls how fast the waves move

    public Wave(int x, int y, int speed) {
        this.mPosition = new Vector3(x, y, 0);
        this.speedScale = speed;
        makePixMap(x, y);
    }

    public Wave(int x, int y) {
        this.mPosition = new Vector3(x, y, 0);
        this.speedScale = 1;
        makePixMap(x, y);
    }

    private void makePixMap(int x, int y) {
        mPixmap.setColor(0xffffffff);
        mPixmap.drawCircle(x, y, 15);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public Vector3 getPosition() {
        return this.mPosition;
    }

    @Override
    public Texture getTexture() {
        return new Texture(mPixmap); // calculate this on each call

    }

}
