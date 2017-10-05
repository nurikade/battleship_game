package com.nurikadewi.battleship.modules.opponent.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nurikadewi.battleship.R;
import com.nurikadewi.battleship.mvp.model.Coordinante;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Nurika Dewi on 10/4/2017.
 * CoordinatesAdapter
 */

public class CoordinatesAdapter extends RecyclerView.Adapter<CoordinatesAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Coordinante> mCoordinateList = new ArrayList<>();

    public CoordinatesAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_coordinate, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mCoordinateList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCoordinateList.size();
    }

    public void addCoordinate(List<Coordinante> coordinanteList) {
        mCoordinateList.addAll(coordinanteList);
        notifyDataSetChanged();
    }

    public void clearCoordinate() {
        mCoordinateList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.img_coordinate)
        protected ImageView mImgCoordinate;

        private Context mContext;
        private Coordinante mCoordinate;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("NewApi")
        public void bind(Coordinante coordinante) {
            mCoordinate = coordinante;
            mImgCoordinate.setImageResource(mCoordinate.getIcon());
        }

        @Override
        public void onClick(View v) {
            if (mCoordinateClickListener != null) {
                mCoordinateClickListener.onClick(itemView, mCoordinate, getAdapterPosition());
            }
        }
    }

    public void setCoordinateClickListener(OnCoordinateClickListener listener) {
        mCoordinateClickListener = listener;
    }

    private OnCoordinateClickListener mCoordinateClickListener;

    public interface OnCoordinateClickListener {

        void onClick(View v, Coordinante coordinante, int position);
    }
}