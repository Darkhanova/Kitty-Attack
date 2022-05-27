package com.mrdeveloper.gravity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mrdeveloper.gravity.scenes.LoaderResourceScene;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

//Главный класс
public class Main extends CoreGame {

    //Метод начального запуска окна
    public SceneGame getStartScene() {
        return new LoaderResourceScene(this);
    }

}
