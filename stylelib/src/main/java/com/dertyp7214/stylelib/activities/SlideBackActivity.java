package com.dertyp7214.stylelib.activities;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SlideBackActivity extends AppCompatActivity implements View.OnTouchListener {

    private View _oldScreen, _nextScreen;
    private int _xDelta;

    private int lastPos = 0;
    private int currentPos = 0;

    int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
    }

    public void setOldScreen(@LayoutRes int layout, @IdRes int v) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup view = findViewById(v);
        view.setLayoutParams(layoutParams);
        LayoutInflater factory = LayoutInflater.from(this);
        view.addView(factory.inflate(layout, null));
        _oldScreen = view;
        _oldScreen.setOnClickListener(l -> openNewScreen());
    }

    public void openNewScreen() {
        RelativeLayout.LayoutParams layoutP =
                (RelativeLayout.LayoutParams) _nextScreen.getLayoutParams();
        ValueAnimator animator =
                ValueAnimator
                        .ofInt(layoutP.leftMargin, 0);
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            int pos = (int) animation.getAnimatedValue();
            if (pos <= width) {
                RelativeLayout.LayoutParams layoutPar =
                        (RelativeLayout.LayoutParams) _nextScreen.getLayoutParams();
                layoutPar.leftMargin = pos;
                _nextScreen.setLayoutParams(layoutPar);
                RelativeLayout.LayoutParams rootParams =
                        (RelativeLayout.LayoutParams) _oldScreen.getLayoutParams();
                rootParams.leftMargin =
                        - ((_oldScreen.getWidth() / 3) - (pos / 3));
                _oldScreen.setLayoutParams(rootParams);
            }
        });
        animator.start();
    }

    public void setNewScreen(@LayoutRes int layout, @IdRes int v) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup view = findViewById(v);
        view.setLayoutParams(layoutParams);
        LayoutInflater factory = LayoutInflater.from(this);
        view.addView(factory.inflate(layout, null));
        _nextScreen = view;
        _nextScreen.setOnTouchListener(this);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams =
                        (RelativeLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                break;
            case MotionEvent.ACTION_UP:
                RelativeLayout.LayoutParams layout =
                        (RelativeLayout.LayoutParams) v.getLayoutParams();
                ValueAnimator animator =
                        ValueAnimator
                                .ofInt(layout.leftMargin,
                                        (lastPos + 10 < currentPos || lastPos >= (width / 10
                                                * 8)) ? width : 0);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int pos = (int) animation.getAnimatedValue();
                    if (pos <= width) {
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = pos;
                        v.setLayoutParams(layoutParams);
                        RelativeLayout.LayoutParams rootParams =
                                (RelativeLayout.LayoutParams) _oldScreen.getLayoutParams();
                        rootParams.leftMargin =
                                - ((_oldScreen.getWidth() / 3) - (pos / 3));
                        _oldScreen.setLayoutParams(rootParams);
                    }
                });
                animator.start();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                lastPos = currentPos;
                currentPos = X;
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                v.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams rootParams =
                        (RelativeLayout.LayoutParams) _oldScreen.getLayoutParams();
                rootParams.leftMargin = - ((_oldScreen.getWidth() / 3) - ((X - _xDelta) / 3));
                _oldScreen.setLayoutParams(rootParams);
                break;
        }
        _oldScreen.invalidate();
        return true;
    }
}
