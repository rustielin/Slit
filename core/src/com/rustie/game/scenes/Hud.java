package com.rustie.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rustie.game.Slit;
import com.rustie.game.utils.Controller;

/**
 * Created by rustie on 5/18/17.
 */

public class Hud implements Disposable{

    private int padding = 10;
    public Stage mStage;
    private Viewport mViewport;

    private int startingSec;
    private int timeInSec;
    private boolean hasTable;

    public Controller controller;

    Label mCountdownLabel;
    Label mScoreLabel;
    Label mTimeLabel;
    Label mLevelLabel;
    Label mLevelNameLabel;
    Label mScoreNameLabel;

    public Hud(SpriteBatch spriteBatch, World world, boolean hasTable) {
        this.hasTable = hasTable;
        startingSec =  (int) (System.currentTimeMillis() / 1000);
        timeInSec = 0;

        controller = new Controller(spriteBatch, world);

//        mViewport = new FitViewport(Slit.WIDTH, Slit.HEIGHT, new OrthographicCamera());
        mViewport = new ScreenViewport(new OrthographicCamera());
        mStage = new Stage(mViewport, spriteBatch);

        if (hasTable) {

            Table table = new Table();
            table.top();
            table.setFillParent(true);

            mCountdownLabel = new Label(String.format("%03d", timeInSec), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            mScoreLabel = new Label(String.format("%06d", Slit.SCORE), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            mScoreNameLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            mLevelNameLabel= new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            mTimeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            mLevelLabel =  new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // temp

            table.add(mScoreNameLabel).expandX().padTop(padding);
            table.add(mLevelNameLabel).expandX().padTop(padding);
            table.add(mTimeLabel).expandX().padTop(padding);

            // second row
            table.row();
            table.add(mScoreLabel).expandX();
            table.add(mLevelLabel).expandX();
            table.add(mCountdownLabel).expandX();

            mStage.addActor(table);
        }
    }

    @Override
    public void dispose() {
        mStage.dispose();
    }

    public void update() {
        if (hasTable) {
            timeInSec = (int) (System.currentTimeMillis() / 1000) - startingSec;
            mCountdownLabel.setText(String.format("%03d", timeInSec));
            mScoreLabel.setText(String.format("%06d", Slit.SCORE));
        }
    }


}
