package com.example.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ROOM database's operations should be offloaded into
//background threads, if it runs on main thread then
//the app becomes unresponsive and crashes

public class MyRepository {
    //The Available Database
    //-ROOM Database

    private ContactDAO contactDAO;
    ExecutorService executorService;
    Handler handler;


    public MyRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        //Used for background Database Operations
        executorService = Executors.newSingleThreadExecutor();

        //Used for updating UI
        handler = new Handler(Looper.getMainLooper());
    }

    //Make respective methods to those in the DAO interface
    public void addContact(Contacts contact){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });
    }

    public void deleteContact(Contacts contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }

}
