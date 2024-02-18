package com.example.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityHandlers {
    Context context;

    public MainActivityHandlers(Context context){
        this.context = context;
    }

    public void onFABClicked(View view){
        Intent i = new Intent(view.getContext(),AddNewContactActivity.class);
        context.startActivity(i);
    }
}
