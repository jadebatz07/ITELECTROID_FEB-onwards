package com.example.sms_and_location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySms extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
	
		
		try{
			Bundle b = arg1.getExtras();
			Object[] pdus = (Object[]) b.get("pdus");
			
			for(int i=0; i<pdus.length; i++)
			{
				SmsMessage sms = SmsMessage.createFromPdu((byte[])pdus[i]);
				String address = sms.getDisplayOriginatingAddress();
				String message = sms.getDisplayMessageBody();
				
				
				Toast.makeText(context, "Sender: "+address+"\nMessage: "+message, Toast.LENGTH_LONG).show();
				
				Location location = new Location(context);
				double lng = location.getLong();
				double lat = location.getLat();
				
				if(message.equals("Paulina"))
				{
					SmsManager m = SmsManager.getDefault();
					m.sendTextMessage(address, "", "\nLongitude: "+lng+" Latitude: "+lat, null, null);
				}
			

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		

	}

}
