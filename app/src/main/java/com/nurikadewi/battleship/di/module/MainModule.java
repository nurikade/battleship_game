package com.nurikadewi.battleship.di.module;

import com.nurikadewi.battleship.api.IApiService;
import com.nurikadewi.battleship.di.scope.PerActivity;
import com.nurikadewi.battleship.mvp.view.IMainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author Nurika Dewi <ikaa.nurr@gmail.com>.
 * @since 8/1/17.
 */

@Module
public class MainModule {

    private IMainView mView;

    public MainModule(IMainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    IApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(IApiService.class);
    }

    @PerActivity
    @Provides
    IMainView provideView() {
        return mView;
    }
}
