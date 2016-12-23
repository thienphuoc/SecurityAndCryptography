package tpgame.securityandcryptography;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayContact extends AppCompatActivity {

    private ListView lvContact;

    private DatabaseReference mDatabase;

    ArrayList<Contact> dsContact;
    ArrayAdapter<Contact> adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        addControls();
        addEvents();
        readAllContactsFromPhone();

    }

    private void addControls(){
        lvContact = (ListView) findViewById(R.id.lvContact);
        dsContact = new ArrayList<>();
        adapterContact = new ArrayAdapter<Contact>(DisplayContact.this,android.R.layout.simple_list_item_1,dsContact);

        lvContact.setAdapter(adapterContact);

    }

    private void addEvents(){

    }

    private void readAllContactsFromPhone() {
        Uri uriContact = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uriContact,null,null,null,null);
        dsContact.clear();
        while(cursor.moveToNext())
        {
            //Tạo cột tên:
            String idName=ContactsContract.Contacts.DISPLAY_NAME;
            int colNameIndex=cursor.getColumnIndex(idName);
            String name=cursor.getString(colNameIndex);

            String idPhone=ContactsContract.CommonDataKinds.Phone.NUMBER;
            int colPhoneIndex=cursor.getColumnIndex(idPhone);
            String phone=cursor.getString(colPhoneIndex);

            Contact contact=new Contact(name,phone);
            dsContact.add(contact);
        }

        mDatabase.child("Contacts").push().setValue(dsContact);

        cursor.close();
        adapterContact.notifyDataSetChanged();
    }

}
