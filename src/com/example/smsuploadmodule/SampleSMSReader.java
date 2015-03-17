package com.example.smsuploadmodule;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


// Class to get inbox and sent folder SMS

public class SampleSMSReader {

	private Context mContext;

	public SampleSMSReader(Context context) {
		mContext = context;
	}

	public static final Uri SMS_Inbox = Uri.parse("content://sms/inbox");
	public static final Uri SMS_Sent = Uri.parse("content://sms/sent");

	public int TotalConversationCount = 0;

	public void GetAllMessages(boolean SaveToLocal) {

		SmsDetails(false);
		SmsDetails(true);
	}

	@SuppressWarnings("deprecation")
	public String getCurrDate() {
		String dt;
		Date cal = Calendar.getInstance().getTime();
		dt = cal.toLocaleString();
		return dt;
	}

	public void SmsDetails(boolean inbox) {

		try {

			String sDirection = "1";
			String sMessageType = "0";
			String SMS_READ_COLUMN = "read";
			String SORT_ORDER = " _id ASC";
			int count = 0;
			Cursor cursor;
			int iLastIDRun = 0;
			try {

				if (inbox == true) {

					sDirection = "2";
				} else {

					sDirection = "1";
				}

			} catch (Exception e) {
				e.printStackTrace();
				iLastIDRun = 0;
			}

			if (inbox == true) {
				cursor = mContext.getContentResolver().query(
						SMS_Inbox,
						new String[] { "_id", "thread_id", "address", "person",
								"date", "body" },
						" _id > " + String.valueOf(iLastIDRun), null,
						SORT_ORDER);
				sMessageType = "1";
			} else {
				cursor = mContext.getContentResolver().query(
						SMS_Sent,
						new String[] { "_id", "thread_id", "address", "person",
								"date", "body" },
						" _id > " + String.valueOf(iLastIDRun), null,
						SORT_ORDER);
				sMessageType = "2";
			}

			String sDetail = "";

			int iCnt = 0;
			int iLimit = 500;

			if (cursor != null) {
				try {

					if (cursor.moveToFirst()) {
						String body = "";
						do {

							iCnt = iCnt + 1;
							if (iCnt > iLimit) {
								break;
							}

							long messageId = cursor.getLong(0);
							long threadId = cursor.getLong(1);
							String address = cursor.getString(2);
							long contactId = cursor.getLong(3);
							String contactId_string = String.valueOf(contactId);
							long timestamp = cursor.getLong(4);
							String sBody = cursor.getString(5);

							sDetail = "";

							String sReturn = "";
							// sMessageThreaded=sMessage;

							String currentDateTimeString = DateFormat
									.getDateTimeInstance().format(
											new Date(timestamp));

							if (address.startsWith("1")) {
								address = address.substring(1);
							}

							String sPhoneMessageID = String.valueOf(messageId);

						} while (cursor.moveToNext());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				cursor.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return null;
	}

}
