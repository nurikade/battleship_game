package com.nurikadewi.battleship.di.components;

import android.content.Context;

import com.nurikadewi.battleship.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author Nurika Dewi <ikaa.nurr@gmail.com>.
 * @since 8/1/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface IAppComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
