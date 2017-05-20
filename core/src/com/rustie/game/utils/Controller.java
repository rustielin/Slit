package com.rustie.game.utils;

import com.badlogic.gdx.Gdx;
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
    Viewport mViewport;
    Stage mStage;

    OrthographicCamera mCam;



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
        Gdx.input.setInputProcessor(mStage);


        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);

        mStage.addActor(touchpad);

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
}
