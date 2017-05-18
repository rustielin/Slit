package com.rustie.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rustie on 5/16/17.
 */

public class Player  {
    private Vector3 mPosition;
    private Vector3 mVelocity;
    private Texture mTexture;
    private Circle mCircle;
    private Pixmap mPixmap;
    private int radius;

    /**
     * Makes Player at starting position x,y
     * @param x
     * @param y
     */
    public Player(int x, int y) {
        this(x, y, 15);
    }

    public Player(int x, int y, int r) {
        radius = r;
        mPosition = new Vector3(x, y, 0); // only using x,y
        mVelocity = new Vector3(0, 0, 0);

        // makes a circle of radius 15
        mPixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
        mPixmap.setColor(0xffffffff);
        mPixmap.fillCircle(radius, radius, radius);
        mTexture = new Texture(mPixmap);

        // for easy calculations
        mCircle = new Circle(x, y, radius);
    }

    public void update(float dt) {

    }

    public Vector3 getPosition() {
        return this.mPosition;
    }

    public Texture getTexture() {
        return mTexture;

    }

    public void dispose() {
        mTexture.dispose();

    }

    public boolean collision() {
//        mCircle.contains(); // contains some pixel
        return false;
    }


}
