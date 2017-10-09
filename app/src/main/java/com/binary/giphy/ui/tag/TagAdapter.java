package com.binary.giphy.ui.tag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.giphy.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by duong on 9/27/2017.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private OnItemClickListener listener;
    private Context context;
    private List<String> tags;

    public TagAdapter(Context context,List<String> tags) {
        this.context = context;
        this.tags = tags;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tag, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        String tag = tags.get(position);
        holder.tv_tag.setText(tag.toUpperCase());
        String gifUrl = "http://media0.giphy.com/media/Zw3oBUuOlDJ3W/200.gif";
        Glide.with(context)
                .load(gifUrl)
                .asGif()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView tv_tag;

        public TagViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
}
