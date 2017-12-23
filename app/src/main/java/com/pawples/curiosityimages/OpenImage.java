package com.pawples.curiosityimages;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import uk.co.senab.photoview.PhotoViewAttacher;

public class OpenImage extends AppCompatActivity {

    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_image);
        supportPostponeEnterTransition();

        String urlString;
        String transition;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                urlString = null;
                transition = null;
            } else {
                urlString = extras.getString("STRING_URL");
                transition = extras.getString("TRANSITION_NAME");
            }
        } else {
            urlString = (String) savedInstanceState.getSerializable("STRING_URL");
            transition = (String) savedInstanceState.getSerializable("TRANSITION_NAME");
        }

        final ImageView img = findViewById(R.id.openImageView);

        img.setTransitionName(transition);

        Glide.with(this)
                .load(urlString)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        attacher = new PhotoViewAttacher(img);
                        return false;
                    }
                })
                .into(img);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                attacher.cleanup();
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        attacher.cleanup();
        supportFinishAfterTransition();
    }
}