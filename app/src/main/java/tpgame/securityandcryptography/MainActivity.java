package tpgame.securityandcryptography;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnReadContact,btnReadSMS,btnReadCallLog,btnSendSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadContact = (Button) findViewById(R.id.btnReadContact);
        btnReadSMS = (Button) findViewById(R.id.btnReadSMS);
        btnReadCallLog = (Button) findViewById(R.id.btnCallLog);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");

        btnReadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(MainActivity.this,DisplayContact.class);
                startActivity(intent);
            }
        });

        btnReadSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(MainActivity.this,ReadSMS.class);
                startActivity(intent);
            }
        });

        btnReadCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(MainActivity.this,CallLog.class);
                startActivity(intent);
            }
        });

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage("01674411864", null, "Một con chuột đã bị sập bẫy!", null, null);
                Toast.makeText(MainActivity.this, "Send SMS Success!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
