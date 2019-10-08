package com.example.superclass.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class BinderService extends Service {
    private ArrayList<Person> personArrayList;


    @Override
    public IBinder onBind(Intent intent) {
        personArrayList=new ArrayList<>();
        return null;
    }

    private IBinder iBinder=new PersonAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            personArrayList.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return personArrayList;
        }
    };

}
