package com.example.superclass.framework.mvvm.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.superclass.R;
import com.example.superclass.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_data_binding);
        DataBean bean=new DataBean("大神",20);
        binding.setUser(bean);

    }
}
