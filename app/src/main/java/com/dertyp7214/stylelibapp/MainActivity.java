package com.dertyp7214.stylelibapp;

import android.os.Bundle;

import com.dertyp7214.stylelib.activities.SlideBackActivity;

public class MainActivity extends SlideBackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOldScreen(R.layout.activity_main2, R.id.oldScreen);
        setNewScreen(R.layout.activity_main3, R.id.newScreen);

        findViewById(R.id.oldScreen).setOnClickListener(v -> openNewScreen());
    }
}
