package com.rustie.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/17/17.
 * <p>
 * Describes a Sprite that
 */

public abstract class PxSprite {

    protected Pixmap mPixmap;

    public PxSprite() {
        mPixmap = new Pixmap(Slit.WIDTH, Slit.HEIGHT, Pixmap.Format.RGBA8888);
    }

    protected abstract void update(float dt);

    protected abstract Vector3 getPosition();

    Texture getTexture() {
        return new Texture(mPixmap);
    }

    public abstract void dispose();
}
