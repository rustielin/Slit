package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    OrthographicCamera mCam;

    public static final int RADIUS = 100;

    // positioning of touch center
    private int xPos = 0;
    private int yPos = 0;

    // testing touchpad
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchKnob;
    private Drawable touchBackground;


    /**
     * Initializes buttons and sets their listeners
     */
    public Controller() {
        mCam = new OrthographicCamera();
        mViewport = new FitViewport(Slit.WIDTH, Slit.HEIGHT, mCam);
        mStage = new Stage(mViewport, Slit.mBatch);

        mStage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (getEucDist((int) x, xPos, (int) y, yPos) > RADIUS / 2) {
                    xPos = (int) x - RADIUS;
                    yPos = (int) y - RADIUS;
                    updateTouchpadLocation(xPos, yPos);
                    touchpad.setVisible(true);

                }

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchpad.setVisible(false);
                super.touchUp(event, x, y, pointer, button);
            }
        });


        Gdx.input.setInputProcessor(mStage);


        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(100, touchpadStyle);
//        touchpad.setBounds(15, 15, RADIUS * 2, RADIUS * 2);

        // for now
        touchpad.setVisible(false);
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
