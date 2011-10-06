package org.badlogic;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogicgames.superjumper.SuperJumper;


public class MetaGunAndroidActivity extends AndroidApplication {
    public void onCreate (android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new SuperJumper(), false);
}
}