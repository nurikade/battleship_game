package com.nurikadewi.battleship.di.components;

import com.nurikadewi.battleship.di.module.MainModule;
import com.nurikadewi.battleship.di.scope.PerActivity;
import com.nurikadewi.battleship.modules.main.MainActivity;

import dagger.Component;

/**
 * @author Nurika Dewi <ikaa.nurr@gmail.com>.
 * @since 8/1/17.
 */

@PerActivity
@Component(modules = MainModule.class, dependencies = IAppComponent.class)
public interface IMainComponent {

    void inject(MainActivity activity);
}
