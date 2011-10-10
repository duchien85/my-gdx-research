package org.badlogic;

import org.gsn.caro.CaroGame;

import com.badlogic.gdx.backends.android.AndroidApplication;


public class MetaGunAndroidActivity extends AndroidApplication {
    public void onCreate (android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new CaroGame(), false);
}
}