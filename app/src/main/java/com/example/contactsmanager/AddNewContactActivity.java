package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;

import com.example.contactsmanager.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding activityAddNewContactBinding;
    private AddNewContactClickHandler addNewContactClickHandler;
    private Contacts contact;
    MyViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        activityAddNewContactBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_new_contact
        );

         myViewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        contact = new Contacts();

        activityAddNewContactBinding.setContact(contact);

        addNewContactClickHandler = new AddNewContactClickHandler(
                this,
                contact,
                myViewModel
        );

        activityAddNewContactBinding.setClickHandler(addNewContactClickHandler);

    }
}