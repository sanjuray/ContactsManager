package com.example.contactsmanager;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanager.databinding.ContactListLayoutBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListLayoutBinding contactListLayoutBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_layout,
                        parent,
                        false
                );
        return new ContactViewHolder(contactListLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contact = contacts.get(position);
        holder.contactListLayoutBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        if(contacts != null) return contacts.size();
        return 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactListLayoutBinding contactListLayoutBinding;

        public ContactViewHolder(@NonNull ContactListLayoutBinding contactListLayoutBinding){
            super(contactListLayoutBinding.getRoot());
            this.contactListLayoutBinding = contactListLayoutBinding;
        }
    }
}
