package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rustie.game.sprites.InteractiveTileObject;

/**
 * Created by rustie on 5/19/17.
 */

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof InteractiveTileObject || fixB.getUserData() instanceof InteractiveTileObject) {
            Fixture object = fixA.getUserData() instanceof InteractiveTileObject ? fixA : fixB;
            Fixture player = object == fixA ? fixB : fixA;

            ((InteractiveTileObject) object.getUserData()).onCollide();

        }


    }

    @Override
    public void endContact(Contact contact) {
//        Gdx.app.log("End Contact", "");

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
