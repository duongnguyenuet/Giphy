package com.binary.giphy.ui.subtag;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.binary.giphy.R;
import com.binary.giphy.models.searchdetail.Data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by duong on 10/3/2017.
 */

public class SubTagAdapter extends RecyclerView.Adapter<SubTagAdapter.SubTagViewHolder> {
    private Context context;
    private List<Data> datas;
    private OnItemClickListener listener;
    private ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#9ACCCD")), new ColorDrawable(Color.parseColor("#8FD8A0")),
                    new ColorDrawable(Color.parseColor("#CBD890")), new ColorDrawable(Color.parseColor("#DACC8F")),
                    new ColorDrawable(Color.parseColor("#D9A790")), new ColorDrawable(Color.parseColor("#D18FD9")),
                    new ColorDrawable(Color.parseColor("#FF6772")), new ColorDrawable(Color.parseColor("#DDFB5C"))
            };

    public SubTagAdapter(Context context, List<Data> datas) {
        this.context = context;
        this.datas = datas;
        Log.e("data", String.valueOf(datas));
    }


    @Override
    public SubTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sub_tag, parent, false);
        return new SubTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubTagViewHolder holder, int position) {
        Data data = datas.get(position);
        Glide.with(context)
                .load(data.getImages().getFixedHeight().getUrl())
                .asGif()
                .placeholder(getRandomDrawbleColor())
                .error(getRandomDrawbleColor())
                .crossFade()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onViewRecycled(SubTagViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public class SubTagViewHolder extends RecyclerView.ViewHolder {
        public GifImageView imageView;

        public SubTagViewHolder(final View itemView) {
            super(itemView);
            imageView = (GifImageView) itemView.findViewById(R.id.img_sub);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }

    public ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }
}
