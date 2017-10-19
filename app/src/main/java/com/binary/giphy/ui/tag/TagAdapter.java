package com.binary.giphy.ui.tag;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.giphy.API.ApiConstants;
import com.binary.giphy.API.ApiResponse;
import com.binary.giphy.API.CallBackCustom;
import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.R;
import com.binary.giphy.di.module.NetModule;
import com.binary.giphy.interfaces.OnResponse;
import com.binary.giphy.models.TagModel;
import com.binary.giphy.models.searchdetail.Data;
import com.binary.giphy.ui.gifview.GifViewActivity;
import com.binary.giphy.ui.subtag.SubTagAdapter;
import com.binary.giphy.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;

/**
 * Created by duong on 9/27/2017.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private OnItemClickListener listener;
    private Context context;
    private List<String> tags;
    private List<TagModel> listTagModels;

    public TagAdapter(Context context,List<String> tags) {
        this.context = context;
        this.tags = tags;
        listTagModels = new ArrayList<>();
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
//        Glide.with(context)
//                .load(gifUrl)
//                .asGif()
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(holder.img);
        if (listTagModels.size() > position && listTagModels.get(position) != null) {
            Glide.with(context)
                    .load(listTagModels.get(position).getUrlPhoto())
                    .asGif()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.img);
            return;
        }
        getCategory(tag, holder);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    private void getCategory(final String tag, final TagViewHolder holder) {
        Call<ApiResponse<List<Data>>> call = NetModule.getClient().create(GiphyAPI.class).getSearchByKeyResult(Constants.API_KEY, tag, 25, 0, "y", "en", "json");
        call.enqueue(new CallBackCustom<ApiResponse<List<Data>>>(context, new OnResponse<ApiResponse<List<Data>>>() {
            @Override
            public void onResponse(final ApiResponse<List<Data>> response) {
                if (response.getMeta().getStatus() == ApiConstants.CODE_SUCESS && response.getData() != null && response.getData().size() > 0) {
                    TagModel model = new TagModel(tag.toUpperCase(), response.getData().get(0).getImages().getFixedHeight().getUrl());
                    listTagModels.add(model);
                    Glide.with(context)
                            .load(model.getUrlPhoto())
                            .asGif()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(holder.img);
                }
            }
        }));
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        public GifImageView img;
        public TextView tv_tag;

        public TagViewHolder(final View itemView) {
            super(itemView);
            img = (GifImageView) itemView.findViewById(R.id.img);
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
