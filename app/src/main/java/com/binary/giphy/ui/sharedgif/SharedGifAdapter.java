package com.binary.giphy.ui.sharedgif;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binary.giphy.R;
import com.binary.giphy.models.GifUpload;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by duong on 10/17/2017.
 */

public class SharedGifAdapter extends RecyclerView.Adapter<SharedGifAdapter.SharedGifViewHolder> {
    private Context context;
    private List<GifUpload> gifUploads;
    private OnItemClickListener listener;

    public SharedGifAdapter(Context context, List<GifUpload> gifUploads) {
        this.context = context;
        this.gifUploads = gifUploads;
        Log.e("gif", String.valueOf(gifUploads.size()));
    }

    @Override
    public SharedGifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sub_tag, parent, false);
        Log.e("csh","sch");
        return new SharedGifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SharedGifViewHolder holder, int position) {
        Glide.with(context)
                .load(gifUploads.get(position).getUrl())
                .asGif()
                .crossFade()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gifUploads.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class SharedGifViewHolder extends RecyclerView.ViewHolder {
        public GifImageView imageView;

        public SharedGifViewHolder(final View itemView) {
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
}
