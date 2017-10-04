package com.nurikadewi.battleship.application;

import android.app.Application;

import com.nurikadewi.battleship.di.components.DaggerIAppComponent;
import com.nurikadewi.battleship.di.components.IAppComponent;
import com.nurikadewi.battleship.di.module.AppModule;

/**
 * @author Nurika Dewi <ikaa.nurr@gmail.com>.
 * @since 8/1/17.
 */

public class BattleShipApp extends Application {

    private IAppComponent mIAppComponent;
    public static final String CONTACT_ID = "contact_id";

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mIAppComponent = DaggerIAppComponent
                .builder()
                .appModule(new AppModule(this, ""))
                .build();
    }

    public IAppComponent getApplicationComponent() {
        return mIAppComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
