package com.nurikadewi.battleship.di.module;

import com.nurikadewi.battleship.api.IApiService;
import com.nurikadewi.battleship.di.scope.PerActivity;
import com.nurikadewi.battleship.mvp.view.IOpponentView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Nurika Dewi on 10/4/2017.
 * OpponentModule
 */
@Module
public class OpponentModule {
    private IOpponentView mView;

    public OpponentModule(IOpponentView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    IApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(IApiService.class);
    }

    @PerActivity
    @Provides
    IOpponentView provideView() {
        return mView;
    }
}
