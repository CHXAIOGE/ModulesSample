package com.xiaoge.graphics.modulessample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.xiaoge.graphics.animategraphics.core.Graphics;
import com.xiaoge.graphics.animategraphics.core.target.Target;
import com.xiaoge.graphics.animategraphics.core.wrapper.DrawableHelper;
import com.xiaoge.graphics.animategraphics.core.wrapper.DrawableWrapper;
import com.xiaoge.graphics.animategraphics.core.wrapper.PlayListener;
import com.xiaoge.graphics.apngsequence.apngdrawable.ApngLoader;
import com.xiaoge.graphics.apngsequencemodule.ApngSequenceModule;
import com.xiaoge.graphics.apngsequencemodule.ApngSupportLoader;
import com.xiaoge.graphics.framesequencemodule.FrameSequenceLoader;
import com.xiaoge.graphics.framesequencemodule.FrameSequenceModule;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView)findViewById(R.id.image);
        ImageView imageView2 = (ImageView)findViewById(R.id.image2);

        ApngLoader.init(this);
        Graphics.get().getRegistry().append(new FrameSequenceModule(), FrameSequenceLoader.class);
        Graphics.get().getRegistry().append(new ApngSequenceModule(), ApngSupportLoader.class);
        InputStream rawImageStream = null;
        try {
            rawImageStream = getAssets().open("2.webp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Graphics.get().load(rawImageStream , new Target() {
                @Override
                public void onLoadFailed() {
                    Log.d("zlei","onLoadFailed");
                }

                @Override
                public void onResourceReady(DrawableWrapper drawableWrapper) {
                    Log.d("zlei","onResourceReady");
                    drawableWrapper.setLoopMode(DrawableHelper.REPEAT_MODE_FINITE);
                    drawableWrapper.setRepeatCount(1);
                    drawableWrapper.setPlayListener(new PlayListener() {
                        @Override
                        public void onPlayFinished() {

                        }
                    });
                    drawableWrapper.start();
                }
            }).into(imageView);
        } catch (Exception e) {
            Log.d("zlei",Log.getStackTraceString(e));
        }

        InputStream rawImageStream2 = null;
        try {
            rawImageStream2 = getAssets().open("3.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Graphics.get().load(rawImageStream2 , new Target() {
                @Override
                public void onLoadFailed() {
                    Log.d("zlei","onLoadFailed");
                }

                @Override
                public void onResourceReady(DrawableWrapper drawableWrapper) {
                    Log.d("zlei","onResourceReady");
                    drawableWrapper.setLoopMode(DrawableHelper.REPEAT_MODE_FINITE);
                    drawableWrapper.setRepeatCount(1);
                    drawableWrapper.setPlayListener(new PlayListener() {
                        @Override
                        public void onPlayFinished() {

                        }
                    });
                    drawableWrapper.start();
                }
            }).into(imageView2);
        } catch (Exception e) {
            Log.d("zlei",Log.getStackTraceString(e));
        }

    }
}
