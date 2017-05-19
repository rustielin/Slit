package com.rustie.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;

/**
 * Created by rustie on 5/18/17.
 */

public class Hud {

    private int padding = 10;
    public Stage mStage;
    private Viewport mViewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label mCountdownLabel;
    Label mScoreLabel;
    Label mTimeLabel;
    Label mLevelLabel;
    Label mLevelNameLabel;
    Label mScoreNameLabel;

    public Hud(SpriteBatch spriteBatch) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        mViewport = new FitViewport(Slit.WIDTH, Slit.HEIGHT, new OrthographicCamera());
        mStage = new Stage(mViewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        mCountdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mScoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mScoreNameLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mLevelNameLabel= new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mTimeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mLevelLabel =  new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // temp

        table.add(mScoreNameLabel).expandX().padTop(padding);
        table.add(mLevelNameLabel).expandX().padTop(padding);
        table.add(mTimeLabel).expandX().padTop(padding);
        table.row();
        table.add(mScoreLabel).expandX();
        table.add(mLevelLabel).expandX();
        table.add(mCountdownLabel).expandX();

        mStage.addActor(table);
    }
}
