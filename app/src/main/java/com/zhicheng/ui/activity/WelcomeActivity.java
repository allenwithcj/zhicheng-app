package com.zhicheng.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zhicheng.R;

import me.wangyuwei.particleview.ParticleView;

/**
 * Created by Donson on 2017/1/19.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_activity);
        ParticleView welcome = (ParticleView) findViewById(R.id.pv_logo);
        Intent intent = new Intent(WelcomeActivity.this, Main.class);
        welcome.setOnParticleAnimListener(() -> {
            WelcomeActivity.this.startActivity(intent);
            WelcomeActivity.this.finish();
        });
        if (getSharedPreferences("Welcome", 0).getInt("isOne", 0) == 1) {
            WelcomeActivity.this.startActivity(intent);
            WelcomeActivity.this.finish();
        } else {
            SharedPreferences sp = getSharedPreferences("Welcome", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("isOne", 1);
            editor.apply();
            welcome.startAnim();
        }
    }
}
