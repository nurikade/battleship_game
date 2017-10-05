package com.nurikadewi.battleship.mvp.model;

import java.util.List;

/**
 * Created by Nurika Dewi on 10/5/2017.
 * Point
 */

public class Point {
    private List<Coordinante> list;
    private int orientation;

    public List<Coordinante> getList() {
        return list;
    }

    public void setList(List<Coordinante> list) {
        this.list = list;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
