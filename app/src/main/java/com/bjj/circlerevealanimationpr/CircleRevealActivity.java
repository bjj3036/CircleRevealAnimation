package com.bjj.circlerevealanimationpr;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;

public class CircleRevealActivity extends AppCompatActivity {

    private ViewGroup mLayoutRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_reveal);
        mLayoutRoot = findViewById(R.id.layout_root);
        mLayoutRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                showCircleReveal();
                mLayoutRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void showCircleReveal() {
        View target = mLayoutRoot;
        target.setVisibility(View.VISIBLE);
        int x = target.getWidth() / 2, y = target.getHeight() / 2;
        Animator animator = ViewAnimationUtils.createCircularReveal(target, x, y, 0, (float) Math.hypot(x, y));
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void hideCircleReveal() {
        View target = mLayoutRoot;
        target.setVisibility(View.VISIBLE);
        int x = target.getWidth() / 2, y = target.getHeight() / 2;
        Animator animator = ViewAnimationUtils.createCircularReveal(target, x, y, (float) Math.hypot(x, y), 0);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLayoutRoot.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    @Override
    public void onBackPressed() {
        hideCircleReveal();
    }
}
