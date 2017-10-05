package com.nurikadewi.battleship.modules.opponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nurikadewi.battleship.R;
import com.nurikadewi.battleship.base.BaseActivity;
import com.nurikadewi.battleship.di.components.DaggerIOpponentComponent;
import com.nurikadewi.battleship.di.module.OpponentModule;
import com.nurikadewi.battleship.modules.opponent.adapter.CoordinatesAdapter;
import com.nurikadewi.battleship.mvp.model.Coordinante;
import com.nurikadewi.battleship.mvp.model.Point;
import com.nurikadewi.battleship.mvp.presenter.OpponentPresenter;
import com.nurikadewi.battleship.mvp.view.IOpponentView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class OpponentActivity extends BaseActivity implements IOpponentView, View.OnClickListener {

    @Bind(R.id.rv_coordinate_list)
    protected RecyclerView mCoordinateList;
    @Bind(R.id.rg_ship)
    protected RadioGroup mRgShip;
    @Bind(R.id.rg_orientation)
    protected RadioGroup mRgOrientation;
    @Bind(R.id.btn_save)
    protected Button mBtnSave;
    @Inject
    protected OpponentPresenter mOpponentPresenter;
    private CoordinatesAdapter mCoordinateAdapter;

    int mShipSelected = 0;
    int mShipOrientation = 0;
    int mShip = 0;

    Point mCarrier, mBattleship, mCruiser, mSubmarine, mDestroyer;

    private List<Coordinante> mCoordinanteData;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initComponent();
    }

    private void initComponent() {
        mCoordinateList.setHasFixedSize(true);
        mCoordinateList.setLayoutManager(new GridLayoutManager(this, 10));
        mCoordinanteData = new ArrayList<>();

        mCarrier = new Point();
        mBattleship = new Point();
        mCruiser = new Point();
        mSubmarine = new Point();
        mDestroyer = new Point();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinante coordinante = new Coordinante();
                coordinante.setX(x);
                coordinante.setY(y);
                coordinante.setIcon(R.drawable.ic_layout_blue);
                mCoordinanteData.add(coordinante);
            }
        }
        mCoordinateAdapter = new CoordinatesAdapter(getLayoutInflater());
        mCoordinateAdapter.setCoordinateClickListener(mCoordinateClickListener);
        mCoordinateAdapter.addCoordinate(mCoordinanteData);
        mCoordinateList.setAdapter(mCoordinateAdapter);
    }

    private int getShipSelected() {
        int selectedId = mRgShip.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.rb_carrier:
                mShipSelected = 5;
                break;
            case R.id.rb_battleship:
                mShipSelected = 4;
                break;
            case R.id.rb_cruiser:
                mShipSelected = 3;
                break;
            case R.id.rb_submarine:
                mShipSelected = 3;
                break;
            case R.id.rb_destroyer:
                mShipSelected = 2;
                break;
        }
        mShip = selectedId;
        return mShipSelected;
    }

    private int getShipOrientation() {
        int selectedId = mRgOrientation.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.rb_horizontal:
                mShipOrientation = 0;
                break;
            case R.id.rb_vertical:
                mShipOrientation = 1;
                break;
        }
        return mShipOrientation;
    }

    Point deploy(Coordinante coordinante, Point point) {
        String coord = coordinante.getX() + "" + coordinante.getY();
        int curentPosition = Integer.parseInt(coord);
        List<Coordinante> list = new ArrayList<>();
        switch (getShipOrientation()) {
            case 0:
                int validY = coordinante.getY() + getShipSelected();
                if (validY <= 10) {
                    coordinante.setIcon(R.drawable.ic_layout_brown);
                    list.add(coordinante);
                    for (int i = 1; i < getShipSelected(); i++) {
                        curentPosition = curentPosition + 1;
                        Coordinante update = mCoordinanteData.get(curentPosition);
                        update.setIcon(R.drawable.ic_layout_brown);
                        list.add(update);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.select_orientation), Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                int validX = coordinante.getX() + getShipSelected();
                if (validX <= 10) {
                    coordinante.setIcon(R.drawable.ic_layout_brown);
                    list.add(coordinante);
                    for (int i = 1; i < getShipSelected(); i++) {
                        curentPosition = curentPosition + 10;
                        Coordinante update = mCoordinanteData.get(curentPosition);
                        update.setIcon(R.drawable.ic_layout_brown);
                        list.add(update);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.select_orientation), Toast.LENGTH_LONG).show();
                }
                break;
        }
        point.setList(list);
        point.setOrientation(mShipOrientation);
        return point;
    }

    void removeShip(Point point) {
        if (point.getList() != null) {
            List<Coordinante> coordinantes = point.getList();
            for (Coordinante coordinante : coordinantes) {
                coordinante.setIcon(R.drawable.ic_layout_blue);
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_opponent;
    }

    @Override
    public void resolveDaggerDependency() {
        DaggerIOpponentComponent.builder()
                .iAppComponent(getApplicationComponent())
                .opponentModule(new OpponentModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    private CoordinatesAdapter.OnCoordinateClickListener mCoordinateClickListener = new CoordinatesAdapter.OnCoordinateClickListener() {
        @Override
        public void onClick(View v, Coordinante coordinante, int position) {
            if (getShipSelected() > 0) {
                switch (mShip) {
                    case R.id.rb_carrier:
                        removeShip(mCarrier);
                        mCarrier = deploy(coordinante, mCarrier);
                        break;
                    case R.id.rb_battleship:
                        removeShip(mBattleship);
                        mBattleship = deploy(coordinante, mBattleship);
                        break;
                    case R.id.rb_cruiser:
                        removeShip(mCruiser);
                        mCruiser = deploy(coordinante, mCruiser);
                        break;
                    case R.id.rb_submarine:
                        removeShip(mSubmarine);
                        mSubmarine = deploy(coordinante, mSubmarine);
                        break;
                    case R.id.rb_destroyer:
                        removeShip(mDestroyer);
                        mDestroyer = deploy(coordinante, mDestroyer);
                        break;
                }
                mCoordinateAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.validate), Toast.LENGTH_LONG).show();
            }
        }
    };
}
