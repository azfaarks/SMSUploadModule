# SMSUploadModule
This project is coded to get All Inbox SMS in Android

MainActivity.java :
has the implementation of how to get SMS in Android

IncomingSMS.java : (extended with Broadcast Receiver)

to implement broadcast reciever need to add it in manifest file : 
  <receiver android:name=".IncomingSms">   
     <intent-filter>
         <action android:name="android.provider.Telephony.SMS_RECEIVED" />
     </intent-filter>
 </receiver>
 You can uncomment it from manifest file to get every single project when it comes to your android phones you can set SMS reciever and use IncomingSMS.java class it will broadcast every single SMS




SampleSMSReader.java : 
Class will get all sms in sent and inbox folder 

SmsModel.java: 
Simple SMS Model class with the Sender number and Message
