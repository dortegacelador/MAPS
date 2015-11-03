package com.android.HolaMundo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

//-- 
import android.widget.TextView;
//--

import com.android.HolaMundo.R;

public class HolaMundoActivity extends Activity {

	
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  String url="https://maps.googleapis.com/maps/api/directions/xml?origin=place_id:ChIJNbSXHCYmQg0RUhUT2ytPcTM&destination=place_id:ChIJz7ZjFH4oQg0RYS0zuSBBoxQ&key=AIzaSyAmNs57YsWmkZPO3CCfPzYvtmFsY1BNQtY";
        String url="http://api.androidhive.info/pizza/?format=xml";
        TextView tv = new TextView(this);
        	
        	tv.setText(getXml(url));
       //  	tv.setText("hola");
            setContentView(tv);      	
        

 //       setContentView(R.layout.main);
        
//      TextView tv = (TextView) findViewById(R.id.texto);
//      tv.setText("Hola DAM2");   
//      tv.setText( R.string.hola );   
        
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    public String getXml(String url)
    {
    	String xml=null;
    	try{
    		DefaultHttpClient httpClient=new DefaultHttpClient();
    		HttpGet httpGet=new HttpGet(url);
    		HttpResponse httpResponse=httpClient.execute(httpGet);
    		
    		if(httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
    		{
    			Log.w(getClass().getSimpleName(),"Error"+httpResponse.getStatusLine().getStatusCode()+"for URL"+ url);
    			return null;
    		}
    		HttpEntity httpEntity=httpResponse.getEntity();
    		xml=EntityUtils.toString(httpEntity);
    	}catch(UnsupportedEncodingException e)
    	{
    		e.printStackTrace();
    	} catch (ParseException e) {
    		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return xml;
    }
    @Override
    protected void onResume() {
    	super.onResume();
    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show(); 
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show(); 
    }
}