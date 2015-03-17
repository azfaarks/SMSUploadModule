package com.example.smsuploadmodule;

public class SmsModel {
	
	
	String _address ; 
	String _body;
	
	
	public SmsModel(String address,String body)
	{
		this._address =address;
		this._body = body;
	}
	
	public String getAddress()
	{
		return _address;
	}
	
	public String getBody()
	{
		return _body;
	}
	
	

}
