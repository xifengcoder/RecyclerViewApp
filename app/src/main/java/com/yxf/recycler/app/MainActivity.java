package com.yxf.recycler.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yxf.recyclerview.widget.LinearLayoutManager;
import com.yxf.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContacts;

    private Button mInsertBtn;
    private Button mModifyBtn;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInsertBtn = findViewById(R.id.btn_insert);
        mModifyBtn = findViewById(R.id.btn_modify);
        mRecyclerView = findViewById(R.id.recycler_view);

        mContacts = Contact.createContactsList(20);
        ContactsAdapter adapter = new ContactsAdapter(mContacts);
        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "position " + position, Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContacts.add(0, new Contact("Barney", true));
                adapter.notifyItemInserted(0);
                mRecyclerView.scrollToPosition(0); // index 0 position
            }
        });

        mModifyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Contact contact = mContacts.get(2);
                contact.setName(contact.getName() + "**");
                adapter.notifyItemChanged(2);
            }
        });

        mRecyclerView.setOnFlingListener(new RecyclerViewSwipeListener(true) {
            @Override
            public void onSwipeDown() {
                Toast.makeText(MainActivity.this, "swipe down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(MainActivity.this, "swipe up", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
