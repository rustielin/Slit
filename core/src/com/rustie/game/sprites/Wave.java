package com.rustie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/17/17.
 */

public class Wave {

    private Vector3 mPosition;
    private Texture mTexture;
    private int speedScale; // controls how fast the waves move
    private Pixmap mPixmap;
    private int radius;
    private Circle mCircle;


    public Wave(int x, int y, int r, int speed) {
        Gdx.gl.glLineWidth(32);
        
        this.radius = r;
        this.mPosition = new Vector3(x, y, 0);
        this.speedScale = speed;
        mCircle = new Circle(x, y, radius);

        makePixMap();
    }


    private void makePixMap() {
        mPixmap = new Pixmap(radius * 2 + 1, radius * 2 + 1, Pixmap.Format.RGBA8888);
        mPixmap.setColor(0xffffffff);
        mPixmap.drawCircle(radius, radius, radius);
        mTexture = new Texture(mPixmap);

        mCircle.setRadius(radius);
    }

    public void update(float dt) {
        if (mCircle.radius * 2 < Slit.HEIGHT || mCircle.radius < Slit.WIDTH) {
            radius += speedScale;
            dispose();
            makePixMap();
        }

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

}
