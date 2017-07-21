package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;


/**
 * Created by rustie on 5/18/17.
 */

public class Controller {

    public static final String TAG = "Controller Widget";


    Viewport mViewport;
    Stage mStage;


    public static final int RADIUS = 100;

    // positioning of touch center
    private int xPos = 0;
    private int yPos = 0;

    // testing touchpad
    public Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchKnob;
    private Drawable touchBackground;
    public Body mB2Body;
    public World mWorld;


    /**
     * Initializes buttons and sets their listeners
     */
    public Controller(SpriteBatch spriteBatch, World world) {
        this.mWorld = world;

        mViewport = new FitViewport(Slit.WIDTH, Slit.HEIGHT, new OrthographicCamera());
        mStage = new Stage(mViewport, spriteBatch);

        BodyDef bdef = new BodyDef();
        bdef.position.set(Slit.HEIGHT / Slit.PPM, Slit.WIDTH / Slit.PPM); // set this programatically later
        bdef.type = BodyDef.BodyType.DynamicBody;
        mB2Body = mWorld.createBody(bdef);

        Gdx.input.setInputProcessor(mStage);

        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(0, touchpadStyle);
        touchpad.setBounds(100, 100, RADIUS * 2, RADIUS * 2);

        mStage.addActor(touchpad);

    }

    public void updateTouchpadLocation(int x, int y) {
        touchpad.setBounds(x, y, RADIUS * 2, RADIUS * 2);
    }

    public float getTouchpadPercentX() {
        return touchpad.getKnobPercentX();
    }

    public float getTouchpadPercentY() {
        return touchpad.getKnobPercentY();
    }

    public void draw() {
        mStage.draw();
    }

    /**
     * Resize controller in the event of screen size change, orientation flip, etc
     * @param width
     * @param height
     */
    public void resize(int width, int height) {
        mViewport.update(width, height);
    }

    private float getEucDist(int x1, int x2, int y1, int y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
