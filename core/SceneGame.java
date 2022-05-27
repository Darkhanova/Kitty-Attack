package com.mrdeveloper.my_framework.core;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.mrdeveloper.my_framework.db.DBHelper;

//Хранит все сцены
public abstract class SceneGame {

    protected CoreGame pCoreGame;
    protected int pSceneWidth;
    protected int pSceneHeight;
    protected GraphicsGame pGraphicsGame;
    protected ContentValues contentValues;

    protected SceneGame(CoreGame coreGame) {
        pCoreGame = coreGame;
        pSceneWidth = coreGame.getGraphicsFW().getWidthFrameBuffer();
        pSceneHeight = coreGame.getGraphicsFW().getHeightFrameBuffer();
        pGraphicsGame = coreGame.getGraphicsFW();
        contentValues  = new ContentValues();
    }
    protected abstract void update();
    protected abstract void drawing();
    protected abstract void pause();
    protected abstract void resume();
    protected abstract void dispose();
}
