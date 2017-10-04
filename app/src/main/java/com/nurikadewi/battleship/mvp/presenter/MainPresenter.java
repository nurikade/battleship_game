package com.nurikadewi.battleship.mvp.presenter;

import com.nurikadewi.battleship.api.IApiService;
import com.nurikadewi.battleship.base.BasePresenter;
import com.nurikadewi.battleship.mvp.view.IMainView;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Nurika Dewi <ikaa.nurr@gmail.com>.
 * @since 8/1/17.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    @Inject
    protected IApiService mApiService;

    @Inject
    protected MainPresenter() {
    }
}
