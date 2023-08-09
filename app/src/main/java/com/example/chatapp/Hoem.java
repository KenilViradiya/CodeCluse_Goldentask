package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivityHoemBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Hoem extends AppCompatActivity {
ActivityHoemBinding binding;
FirebaseDatabase database;
ArrayList<User> users;
UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding = ActivityHoemBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
database = FirebaseDatabase.getInstance();
users = new ArrayList<>();
usersAdapter = new UsersAdapter(this,users);
binding.recyclerView.setAdapter(usersAdapter);
database.getReference().child("users").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        users.clear();
        for(DataSnapshot snapshot1: snapshot.getChildren()){
            User user = snapshot1.getValue(User.class);
            users.add(user);

        }
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);

        // Set the Toolbar as the app bar for the activity
        setSupportActionBar(toolbar);
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return true;
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            Toast.makeText(this, "Serach Clicked", Toast.LENGTH_SHORT).show();
            // Handle search item click
            // Add your code here
            return true;
        } else if (id == R.id.logout) {
            // Handle logout item click
            // Add your code here
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(Hoem.this,MainActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.updaateprofile) {
            // Handle logout item click
            // Add your code here
            Intent i = new Intent(Hoem.this,profileset.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
