package com.mrdeveloper.gravity.tasks;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.interfaces.TaskCompleteListener;
import com.mrdeveloper.gravity.scenes.LoaderResourceScene;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.GraphicsGame;

import java.util.ArrayList;

public class  LoaderTask extends AsyncTask<Void, Integer, Void> {

    private CoreGame mCoreGame;
    private TaskCompleteListener mTaskCompleteListener;

    public LoaderTask(CoreGame coreGame, TaskCompleteListener taskCompleteListener) {
        mCoreGame = coreGame;
        mTaskCompleteListener = taskCompleteListener;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LoaderResourceScene.setProgressLoader(values[0]);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Void doInBackground(Void... voids) {
        loaderAssets();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTaskCompleteListener.onComplete();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loaderAssets() {
        loadTexture(mCoreGame.getGraphicsFW());
        publishProgress(100);
        loadSpritePlayer(mCoreGame.getGraphicsFW());
        publishProgress(300);

        loadSpriteEnemy(mCoreGame.getGraphicsFW());
        publishProgress(500);
        loadOther(mCoreGame.getGraphicsFW());
        publishProgress(600);
        loadAudio(mCoreGame);

        loadSpritePlayerShieldsOn(mCoreGame.getGraphicsFW());
        publishProgress(700);
        loadGifts(mCoreGame.getGraphicsFW());
        publishProgress(800);
    }

    private void loadGifts(GraphicsGame graphicsGame) {
        //Метод загружает подарки
        ResourceGame.sSpriteProtector = new ArrayList<>();
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                595, 1, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                629, 1, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                663, 1, 32, 32));
        ResourceGame.sSpriteProtector.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                697, 1, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsGame graphicsGame) {
        //Метод загружает спрайты игрока с включенными щитами
        ResourceGame.sSpritePlayerShieldsOn = new ArrayList<>();
        ResourceGame.sSpritePlayerShieldsOnBoost = new ArrayList<>();
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                331, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                397, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                463, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOn.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                529, 1, 64, 64));

        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                1193, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                1193, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                1193, 1, 64, 64));
        ResourceGame.sSpritePlayerShieldsOnBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                1193, 1, 64, 64));
    }

    private void loadAudio(CoreGame coreGame) {
        //Мето загружает музыку и звуки
        ResourceGame.sMainMusicGame = coreGame.getAudioFW().newMusic("music.ogg");
        ResourceGame.sSoundHit = coreGame.getAudioFW().newSound("hit.ogg");
        ResourceGame.sSoundExplode = coreGame.getAudioFW().newSound("explode.ogg");
        ResourceGame.sSoundTouch = coreGame.getAudioFW().newSound("touch.ogg");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadOther(GraphicsGame graphicsGame) {
        ResourceGame.sShieldHitEnemy = graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64);
        SettingsGame.loadSettings(mCoreGame);
        ResourceGame.mainMenuFont = mCoreGame.getResources().getFont(R.font.russo_one);
    }

    private void loadSpriteEnemy(GraphicsGame graphicsGame) {
        //Метод загружает спрайты врагов
        ResourceGame.sSpriteEnemy = new ArrayList<>();
        ResourceGame.sSpriteEnemy2 = new ArrayList<>();
        ResourceGame.sSpriteEnemy3 = new ArrayList<>();

        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 731, 1,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 797, 1,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 863, 1,
                64, 64));
        ResourceGame.sSpriteEnemy.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 929, 1,
                64, 64));

        ResourceGame.sSpriteEnemy2.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 731, 1,
                64, 64));
        ResourceGame.sSpriteEnemy2.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 797, 1,
                64, 64));
        ResourceGame.sSpriteEnemy2.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 863, 1,
                64, 64));
        ResourceGame.sSpriteEnemy2.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 929, 1,
                64, 64));

        ResourceGame.sSpriteEnemy3.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 731, 1,
                64, 64));
        ResourceGame.sSpriteEnemy3.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 797, 1,
                64, 64));
        ResourceGame.sSpriteEnemy3.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 863, 1,
                64, 64));
        ResourceGame.sSpriteEnemy3.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 929, 1,
                64, 64));
    }

    private void loadSpritePlayer(GraphicsGame graphicsGame) {
        //Метод загружает спрайты игрока без щитов
        ResourceGame.sSpritePlayer = new ArrayList<>();
        ResourceGame.sSpritePlayerBoost = new ArrayList<>();
        ResourceGame.sSpriteExplosionPlayer = new ArrayList<>();

        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                265, 1, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                265, 1, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                265, 1, 64, 64));
        ResourceGame.sSpriteExplosionPlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas,
                265, 1, 64, 64));


        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64));
        ResourceGame.sSpritePlayer.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64));

        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 265, 1,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 995, 1,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 1061, 1,
                64, 64));
        ResourceGame.sSpritePlayerBoost.add(graphicsGame.newSprite(ResourceGame.sTextureAtlas, 1127, 1,
                64, 64));

    }

    private void loadTexture(GraphicsGame graphicsGame) {
        //Загрузка текстур
        ResourceGame.sTextureAtlas = graphicsGame.newTexture("texture_atlas.png");
    }

}
