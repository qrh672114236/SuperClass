package com.example.superclass.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superclass.R;

public class BinderActivity extends AppCompatActivity {
    private PersonAidl aidl;

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidl=PersonAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);


        startService(new Intent(this,BinderService.class));


        //服务端
        //bindService();

       // sendMsg();
    }

    private void sendMsg() {
        try {
            aidl.addPerson(new Person("大傻子",25));
            aidl.getPersonList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindService() {
        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.example.superclass.binder","com.example.superclass.binder.BinderService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }
}
