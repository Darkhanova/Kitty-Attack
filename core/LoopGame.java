package com.mrdeveloper.my_framework.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;

public class LoopGame extends SurfaceView implements Runnable {

    //region Fields
    private final float FPS = 60;
    private final float SECOND = 1000000000;
    private final float UPDATE_TIME = SECOND / FPS;

    private boolean mRunning = false;

    private Thread mGameThread = null;
    private CoreGame mCoreGame;
    private Bitmap mFrameBuffer;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Rect mRect;
    //endregion

    public LoopGame(CoreGame coreGame, Bitmap frameBuffer) {
        super(coreGame);
        mFrameBuffer = frameBuffer;
        mCoreGame = coreGame;
        //Настройка полотна
        mSurfaceHolder = getHolder();
        mRect = new Rect();
        mCanvas = new Canvas();
    }

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;
        while (mRunning) {
            float nowTime = System.nanoTime();
            float elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            delta += elapsedTime / UPDATE_TIME;
            if (delta > 1) {
                updateGame();
                drawingGame();
                delta--;
            }
        }
    }

    private void updateGame() {
        mCoreGame.getCurrentScene().update();
    }

    private void drawingGame() {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.getClipBounds(mRect);
            //на полотно рисуем фреймбуффер, для размерности полотна таких же как у телефона
            mCanvas.drawBitmap(mFrameBuffer, null, mRect, null);
            mCoreGame.getCurrentScene().drawing();
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    //метод старта игры
    public void startGame() {
        if (mRunning) {
            return;
        }
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public void stopGame() {
        if (!mRunning) {
            return;
        }
        mRunning = false;

        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
