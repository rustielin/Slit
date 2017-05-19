package com.rustie.game.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rustie.game.states.State;
import com.rustie.game.utils.PolygonShapeDrawer;

/**
 * Created by rustie on 5/18/17.
 */

public class PolygonShapeTest{
    PolygonShapeDrawer drawer;
    PolygonSpriteBatch batch;
    Stage stage;
    Skin skin;
    Actor ellipse;
    TextureRegion white;

    public void create() {
        drawer = new PolygonShapeDrawer();
        batch = new PolygonSpriteBatch();
        stage = new Stage(new ScreenViewport(), batch);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        white = skin.getAtlas().findRegion("white");
        white.setRegion(white.getRegionX() + 1, white.getRegionY() + 1, 1, 1);

        TextButton button = new TextButton("Behind the shape!", skin);
        button.setPosition(80, 120);
        stage.addActor(button);

        ellipse = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                drawer.setTextureRegion(white);
                float w = getWidth(), h = getHeight();
                drawer.setColor(getColor());
                drawer.ellipse(w, h, w * 0.5f, h * 0.5f, 20, getX() + w * 0.5f, getY() + h * 0.5f, 0, 0, 0, -1);
                drawer.draw((PolygonSpriteBatch) batch);
            }
        };
        ellipse.setSize(100, 100);
        ellipse.setPosition(100, 100);
        ellipse.setColor(Color.YELLOW);
        stage.addActor(ellipse);

        button = new TextButton("In front of the shape!", skin);
        button.setPosition(80, 160);
        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        stage.act();
        stage.draw();
    }

}