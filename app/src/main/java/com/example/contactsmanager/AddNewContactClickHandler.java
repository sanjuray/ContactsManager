package com.example.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    private Context context;
    private Contacts contact;
    private MyViewModel myViewModel;

    public AddNewContactClickHandler(Context context, Contacts contact,MyViewModel myViewModel) {
        this.context = context;
        this.contact = contact;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view){
        if(contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context, "Fields Cannot be Empty!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("name",contact.getName());
//            intent.putExtra("email",contact.getEmail());
            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );
            myViewModel.addNewContact(c);
            context.startActivity(intent);
        }
    }
}
