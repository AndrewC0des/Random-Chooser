package com.a02332358.randomchooser;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a02332358.randomchooser.fragments.DisplaySetFragment;

public class MainActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerView, DisplaySetFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}