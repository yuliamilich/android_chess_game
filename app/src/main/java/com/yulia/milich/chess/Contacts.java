package com.yulia.milich.chess;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    private ListView contactsList;
    private ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsArrayList();

        contactsList = findViewById(R.id.contactsList);
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsArrayList());
        contactsList.setAdapter(adp);

    }

    private ArrayList<String> contactsArrayList() {
        ArrayList<String> contacts = new ArrayList<String>();

//        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//        while (c.moveToNext()) {
//            String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            contacts.add(contactName);


        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (c.moveToNext()) {
            String contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contacts.add(contactName);

////            ContactsContract.Contacts.CONTENT_URI;
//            Android_Contact android_contact = new Android_Contact();
//            String contact_id = cursor_Android_Contacts.getString(cursor_Android_Contacts.getColumnIndex(ContactsContract.Contacts._ID));
//            String contact_display_name = cursor_Android_Contacts.getString(cursor_Android_Contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }
        c.close();
        return contacts;
    }
}
