package com.mrdeveloper.my_framework.core;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mrdeveloper.my_framework.db.DBHelper;

//Ядро игры
//Класс создания окна
public class CoreGame extends AppCompatActivity {

    //Соотношение сторон
    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;


    //Луп - фпс. Главный цикл игры
    private LoopGame mLoopGame;
    //Графика
    private GraphicsGame mGraphicsGame;
    //Обработка нажатий на экран
    private TouchListenerGame mTouchListenerGame;

    //Музыка
    private AudioGame mAudioGame;
    private Display mDisplay;
    private Point mSizeDisplay;
    private Bitmap mFrameBuffer;
    private SceneGame mSceneGame;
    private float mSceneWidth;
    private float mSceneHeight;
    private SharedPreferences mSharedPreferences;
    private final String SETTINGS = "settings";
    private boolean mIsPressedKeyBack;
    public DBHelper dbHelper;
    public SQLiteDatabase database;

//endregion

    //Супер главный метод. При запуске приложения, он создает окно
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //БД
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        //Показ окна
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        init();
        setContentView(mLoopGame);
    }
//инициализация полей
    public void init() {
        mSharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        mSizeDisplay = new Point(); //Размер дисплея
        mDisplay = getWindowManager().getDefaultDisplay(); //Получает всю инфу о размерах дисплея телефона
        mDisplay.getSize(mSizeDisplay); //Получает только размер экрана
        mFrameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888); //Заносим в бит мап
        mSceneWidth = FRAME_BUFFER_WIDTH / mSizeDisplay.x; //Длина экрана
        mSceneHeight = FRAME_BUFFER_HEIGHT / mSizeDisplay.y; //Высота экрана
        mAudioGame = new AudioGame(this); //Звуки игры
        mLoopGame = new LoopGame(this, mFrameBuffer); //Главный цикл
        mGraphicsGame = new GraphicsGame(getAssets(), mFrameBuffer); //Графика
        mTouchListenerGame = new TouchListenerGame(mLoopGame, mSceneWidth, mSceneHeight); //Обработка нажатий
        mSceneGame = getStartScene(); //Старт показа сцены
        mIsPressedKeyBack = false;
    }

    //Выход из паузы
    public void onResume() {
        super.onResume();
        mSceneGame.resume();
        mLoopGame.startGame();
    }

    //Вход в паузу
    public void onPause() {
        super.onPause();
        mSceneGame.pause();
        mLoopGame.stopGame();
        if (isFinishing()) {
            mSceneGame.dispose();
        }
    }

    //Вылет
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mIsPressedKeyBack = true;
            return true;
        }
        return false;
    }

    //region Get&Set
    public boolean isPressedKeyBack() {
        return mIsPressedKeyBack;
    }

    public void setPressedKeyBack(boolean mIsPressedKeyBack) {
        this.mIsPressedKeyBack = mIsPressedKeyBack;
    }

    public GraphicsGame getGraphicsFW() {
        return mGraphicsGame;
    }

    public TouchListenerGame getTouchListenerFW() {
        return mTouchListenerGame;
    }

    public void setScene(SceneGame sceneGame) {
        if (sceneGame == null) {
            throw new IllegalArgumentException("Не возможно загрзуить сцену");
        }
        this.mSceneGame.pause();
        this.mSceneGame.dispose();
        sceneGame.resume();
        sceneGame.update();
        this.mSceneGame = sceneGame;
    }

    public SceneGame getCurrentScene() {
        return mSceneGame;
    }

    public SceneGame getStartScene() {
        return mSceneGame;
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public AudioGame getAudioFW() {
        return mAudioGame;
    }
    //endregion


}
