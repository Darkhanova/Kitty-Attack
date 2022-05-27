package com.mrdeveloper.gravity.scenes;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.my_framework.db.DBHelper;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class TopDistanceScene extends SceneGame {

    private int max;


    TopDistanceScene(CoreGame coreGame) {
        super(coreGame);
        //Взятие значения из бд
        //===============================
        @SuppressLint("Recycle")
        Cursor cursor = coreGame.database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,DBHelper.KEY_DISTANCE);
        cursor.moveToFirst();
            do {
                int disIndex = cursor.getColumnIndex(DBHelper.KEY_DISTANCE);
                if(max < cursor.getInt(disIndex)){
                    max = cursor.getInt(disIndex);// вот нашли макс.дистанцию
                }
                System.out.println("dis " + cursor.getInt(disIndex));
            }while (cursor.moveToNext());

        //=============================

    }

    @Override
    public void update() {
        if (pCoreGame.getTouchListenerFW().getTouchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }
    }

    @Override
    public void drawing() {
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_top_distance), 120, 200, Color.rgb(255, 182, 193), 40, null);
        pGraphicsGame.drawText(String.valueOf(max), 120, 250, Color.rgb(255, 182, 193), 50, null);
    }

    //region @Override
    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        pGraphicsGame.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
    //endregion
}
