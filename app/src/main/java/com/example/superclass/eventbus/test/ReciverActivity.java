package com.example.superclass.eventbus.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.superclass.R;
import com.example.superclass.eventbus.EventBus;
import com.example.superclass.eventbus.Subsribe;

public class ReciverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciver);

        EventBus.getDefault().register(this);
        startActivity(new Intent(ReciverActivity.this,EventBusActivity.class));

    }

    @Subsribe()
    public void receve(Bean bean){
        Toast.makeText(this,bean.name+"今年"+bean.age+"岁",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
