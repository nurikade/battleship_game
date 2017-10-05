package com.nurikadewi.battleship.modules.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nurikadewi.battleship.R;
import com.nurikadewi.battleship.base.BaseActivity;
import com.nurikadewi.battleship.di.components.DaggerIMainComponent;
import com.nurikadewi.battleship.di.module.MainModule;
import com.nurikadewi.battleship.modules.opponent.OpponentActivity;
import com.nurikadewi.battleship.mvp.presenter.MainPresenter;
import com.nurikadewi.battleship.mvp.view.IMainView;

import javax.inject.Inject;
import butterknife.Bind;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener {

    @Bind(R.id.text_player)
    protected TextView mTextPlayer;
    @Bind(R.id.btn_find_player)
    protected Button mBtnFindPlayer;
    @Bind(R.id.btn_play)
    protected Button mBtnPlay;
    @Inject
    protected MainPresenter mMainPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initComponent();
    }

    private void initComponent() {
        mBtnFindPlayer.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void resolveDaggerDependency() {
        DaggerIMainComponent.builder()
                .iAppComponent(getApplicationComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find_player:

                break;
            case R.id.btn_play:
                startActivity(new Intent(this, OpponentActivity.class));
                break;
        }
    }
}
