package com.rustie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rustie on 5/18/17.
 */

public class Controller {
    Viewport mViewport;
    Stage mStage;
    boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean slowPressed;
    OrthographicCamera mCam;

    int buttonSize = 15;
    int padSize = 5;

    /**
     * Initializes buttons and sets their listeners
     */
    public Controller() {
        mCam = new OrthographicCamera();
        mViewport = new FitViewport(Slit.WIDTH, Slit.HEIGHT, mCam);
        mStage = new Stage(mViewport, Slit.mBatch);
        Gdx.input.setInputProcessor(mStage);

        Table table = new Table();
        table.left().bottom(); // at the left bottom corner


        // initializing all buttons and their listeners
        Image upImg = new Image(new Texture("flatDark25.png"));
        upImg.setSize(buttonSize, buttonSize);
        upImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });

        Image downImg = new Image(new Texture("flatDark26.png"));
        downImg.setSize(buttonSize, buttonSize);
        downImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
            }
        });

        Image leftImg = new Image(new Texture("flatDark23.png"));
        leftImg.setSize(buttonSize, buttonSize);
        leftImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightImg = new Image(new Texture("flatDark24.png"));
        rightImg.setSize(buttonSize, buttonSize);
        rightImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        // arranging the buttons iin the table
        table.add();
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
        table.add();

        table.row().pad(padSize, padSize, padSize, padSize);
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table.add();
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());

        table.row().padBottom(padSize);
        table.add();
        table.add(downImg).size(downImg.getWidth(), downImg.getWidth());
        table.add();

        mStage.addActor(table);



        // another one for slow down key
        Table table2 = new Table();
        table2.right().bottom();

        Image slowImg = new Image(new Texture("flatDark25.png"));
        slowImg.setSize(buttonSize, buttonSize);
        slowImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                slowPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                slowPressed = false;
            }
        });

        table2.add(slowImg).size(slowImg.getWidth(), slowImg.getHeight());
        mStage.addActor(table2);

    }

    public void draw() {
        mStage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isSlowPressed() {
        return slowPressed;
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
