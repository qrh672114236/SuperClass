package com.example.pluginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pluginstand.InterfaceActivity;

public class BaseActivity extends Activity implements InterfaceActivity {
    protected  Activity that;
    @Override
    public void attach(Activity proxyActivity) {
        this.that=proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if (that!=null){
            that.setContentView(view);
        }else{
            super.setContentView(view);
        }
    }

    @Override
    public View findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        Intent m=new Intent();
        m.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(m);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (that!=null){
            that.setContentView(layoutResID);
        }else{
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstance(Bundle outState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
