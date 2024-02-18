package com.example.contactsmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // DataSource
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList;

    //Adapter
    private MyAdapter myAdapter;

    //Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityHandlers mainActivityHandlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsArrayList = new ArrayList<>();

        activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main);

        mainActivityHandlers = new MainActivityHandlers(this);

        activityMainBinding.setClickHandler(mainActivityHandlers);

        RecyclerView recyclerView = activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Adapter
        myAdapter = new MyAdapter(contactsArrayList);

        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //MyViewModel
        MyViewModel viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        //Inserting contact for testing
//        viewModel.addNewContact(new Contacts("Jack","jack@gmail.com"));
//        viewModel.deleteContact(new Contacts("Jack","jack@gmail.com"));

        //Load data from the database
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();
                for(Contacts c: contacts){
//                    Log.v("TAGY",c.getName());
                    contactsArrayList.add(c);
//                    viewModel.deleteContact(c);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(myAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}