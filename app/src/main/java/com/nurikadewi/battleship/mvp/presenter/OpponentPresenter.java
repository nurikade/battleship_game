package com.nurikadewi.battleship.mvp.presenter;

import com.nurikadewi.battleship.api.IApiService;
import com.nurikadewi.battleship.base.BasePresenter;
import com.nurikadewi.battleship.mvp.view.IOpponentView;

import javax.inject.Inject;

/**
 * Created by Nurika Dewi on 10/4/2017.
 * OpponentPresenter
 */

public class OpponentPresenter extends BasePresenter<IOpponentView> {

    @Inject
    protected IApiService mApiService;

    @Inject
    protected OpponentPresenter() {
    }
}
