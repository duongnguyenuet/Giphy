package com.binary.giphy.ui.sharedgif;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.binary.giphy.models.GifUpload;
import com.binary.giphy.ui.gifupload.GifUploadActivity;
import com.binary.giphy.ui.gifview.GifViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedGifActivity extends BaseActivity implements SharedGifMvpView {
    public static final String INTENT_VIEW = "view";

    @BindView(R.id.rv_shared)
    RecyclerView rvShared;

    private DatabaseReference mDatabaseRef;
    private ArrayList<GifUpload> gifUploadList = new ArrayList<>();
    private SharedGifAdapter adapter;
    private ProgressDialog dialog;
    @Inject
    SharedGifPresenter<SharedGifMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shared_gif);
        getAppComponent().inject(this);
        mPresenter.attachView(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Waiting for loading gifs");
        dialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(GifUploadActivity.FB_DATABASE_PATH);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dialog.dismiss();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    GifUpload gifUpload = snapshot.getValue(GifUpload.class);
                    gifUploadList.add(gifUpload);
                }

                adapter = new SharedGifAdapter(SharedGifActivity.this,gifUploadList);
                rvShared.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                rvShared.setHasFixedSize(true);
                rvShared.setAdapter(adapter);
                adapter.setOnItemClickListener(new SharedGifAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Intent intent = new Intent(SharedGifActivity.this, GifViewActivity.class);
                        intent.putExtra(INTENT_VIEW, gifUploadList.get(position).getUrl());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
    }
}
