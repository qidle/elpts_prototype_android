package com.elpts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.exit)
    MaterialButton exitButton;

    private ImageSwitcher mImageSwitcher;
    private int[] mImageIds = {R.drawable.reclama_1, R.drawable.reclama_2,
            R.drawable.reclama_3};
    private int mCurIndex;


    public static Intent intentFor(Context uiContext) {
        return new Intent(uiContext, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Электронный ПТС");
        ButterKnife.bind(this);
        mImageSwitcher = findViewById(R.id.imageSwitcher);

        Animation slideInLeftAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        Animation slideOutRight = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        mImageSwitcher.setInAnimation(slideInLeftAnimation);
        mImageSwitcher.setOutAnimation(slideOutRight);

        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        mCurIndex = 0;
        mImageSwitcher.setImageResource(mImageIds[mCurIndex]);


        Timer myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() { // Определяем задачу
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurIndex == mImageIds.length - 1) {
                            mCurIndex = 0;
                            mImageSwitcher.setImageResource(mImageIds[mCurIndex]);
                        } else {
                            mImageSwitcher.setImageResource(mImageIds[++mCurIndex]);
                        }
                    }
                });
            }
        }, 0L, 1000); // интервал - 1000 миллисекунд на прокрутку, 0 миллисекунд до первого запуска.

        exitButton.setOnClickListener((b) -> {
            finish();
        });
    }
}
