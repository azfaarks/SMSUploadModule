package com.example.smsuploadmodule;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	
	List<SmsModel> smsList = new ArrayList<SmsModel>();
	String address,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
               String msgData = "";
               for(int idx=0;idx<cursor.getColumnCount();idx++)
               {
                   msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                   
                   
                   if(cursor.getColumnName(idx).equalsIgnoreCase("address"))
                   {
                	   address = cursor.getString(idx);
                   }
                   if(cursor.getColumnName(idx).equalsIgnoreCase("body"))
                   {
                	   body =   cursor.getString(idx);
                   }
               }
               
               
               smsList.add(new SmsModel(address, body));
               // use msgData
            } while (cursor.moveToNext());
        } else {
           // empty box, no SMS
        }
        
        
        
        for (SmsModel sms : smsList) {
			Log.e("Sms Address & SMS", sms.getAddress() + ": " + sms.getBody());
        	
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
