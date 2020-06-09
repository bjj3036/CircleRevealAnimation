package com.bjj.circlerevealanimationpr;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(v -> {
            View target = findViewById(R.id.layout_circle_reveal);
            target.setVisibility(View.VISIBLE);
            int x = target.getWidth() / 2, y = target.getHeight() / 2;
            Animator animator = ViewAnimationUtils.createCircularReveal(target, x, y, 0, (float) Math.hypot(x, y));
            animator.setDuration(1000);
            animator.start();
        });
        findViewById(R.id.btn_start_activity).setOnClickListener(v -> {
            startActivity(new Intent(this, CircleRevealActivity.class));
            overridePendingTransition(0, 0);
        });
    }
}
