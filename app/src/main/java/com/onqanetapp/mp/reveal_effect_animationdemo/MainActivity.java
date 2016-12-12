package com.onqanetapp.mp.reveal_effect_animationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnShow = (Button) findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // The view
                final View myView = findViewById(R.id.imageView);
                // get the center for the clipping circle
                int cx = myView.getWidth() / 2;
                int cy = myView.getHeight() / 2;
                // get the final radius for the clipping circle
                float radus = (float) Math.hypot(cx, cy);

                if (visible == false) {
                    // create the animator for this view (the start radius is zero)
                    //for openning the view
                    Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, radus);
                    // make the view visible and start the animation
                    myView.setVisibility(View.VISIBLE);
                    anim.start();
                    visible = true;
                } else {
                    // create the animation (the final radius is zero)
                    //for clousing the view
                    Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, radus, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        // make the view invisible when the animation is done
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.INVISIBLE);
                            visible = false;
                        }
                    });

                    anim.start();
                }

            }
        });

    }
}
