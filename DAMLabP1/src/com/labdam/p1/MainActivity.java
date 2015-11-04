
package com.labdam.p1;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.labdam.p1.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.net.Uri;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.com.labdam.p2.MESSAGE";
    XmlPullParserFactory factory;
    private CheckBox chkCrema;
    private CheckBox chkAzucar;
    private  TextView tv;
    private RadioButton cafeDescafeinado;
    private RadioButton cafeExpresso;
    private RadioButton cafeSolidario;

    private Button btnBuscar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    tv = (TextView) findViewById(R.id.textView2);
	    chkCrema         = (CheckBox) findViewById(R.id.chkCrema);
	    chkAzucar        = (CheckBox) findViewById(R.id.chkAzucar);
		
	    cafeDescafeinado = (RadioButton) findViewById(R.id.cafeDescafeinado);
	    cafeExpresso     = (RadioButton) findViewById(R.id.cafeExpresso);
	    cafeSolidario    = (RadioButton) findViewById(R.id.cafeSolidario);

	    btnBuscar = (Button) findViewById(R.id.btnBuscar);

		btnBuscar.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
					/*String myUriString = "https://maps.googleapis.com/maps/api/directions/xml?origin=place_id:ChIJNbSXHCYmQg0RUhUT2ytPcTM&destination=place_id:ChIJz7ZjFH4oQg0RYS0zuSBBoxQ&key=AIzaSyAmNs57YsWmkZPO3CCfPzYvtmFsY1BNQtY";
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myUriString));
					startActivity(intent);*/
				XmlParser task=new XmlParser();
	            task.execute(new String[]{"https://maps.googleapis.com/maps/api/directions/xml?origin=place_id:ChIJNbSXHCYmQg0RUhUT2ytPcTM&destination=place_id:ChIJz7ZjFH4oQg0RYS0zuSBBoxQ&key=AIzaSyAmNs57YsWmkZPO3CCfPzYvtmFsY1BNQtY"});
		        }
		});				
	}

    public void pagar(View view) {    	
        Intent intent = new Intent(this, NuevaActivity.class);
        intent.putExtra( EXTRA_MESSAGE, textoCafe() );
        startActivity(intent);
    }
	
    String textoCafe()
    {
    	String cadena = "Pedido: cafe ";
    	
        if (cafeDescafeinado.isChecked() ) {
        	cadena += "Descafeinado";
        } else if (cafeExpresso.isChecked() ) {
        	cadena += "Expresso";
        } else if (cafeSolidario.isChecked() ) {
        	cadena += "Solidario";
        }
        
        if (chkAzucar.isChecked() ) {
        	cadena += " con azucar";
        }
    	
        if (chkCrema.isChecked() ) {
            if (chkAzucar.isChecked() ) {
            	cadena += " y crema de leche";
            }
            else
            {
            	cadena += " con crema de leche";
            }
        }

        return cadena;
    }
    private class XmlParser extends AsyncTask<String, Void, String>{
    	
    	@Override
       
        protected String doInBackground(String... urls) {
        	String xml=null;
        	try{
        		DefaultHttpClient httpClient=new DefaultHttpClient();
        		for (String url : urls) {
        		HttpGet httpGet=new HttpGet(url);
        		HttpResponse httpResponse=httpClient.execute(httpGet);
        		
        		if(httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
        		{
        			Log.w(getClass().getSimpleName(),"Error"+httpResponse.getStatusLine().getStatusCode()+"for URL"+ url);
        			return null;
        		}
        		HttpEntity httpEntity=httpResponse.getEntity();
        		xml=EntityUtils.toString(httpEntity);
        		}
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

        
        }
        protected void onPostExecute(String result) {
        	try {
				parse(result);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
          tv.setText(result);
        }

        public String parse(String xml) throws XmlPullParserException, IOException
        {
        	factory=XmlPullParserFactory.newInstance();
        	factory.setNamespaceAware(true);
        	XmlPullParser xpp=factory.newPullParser();
        	xpp.setInput(new StringReader(xml));
        	int eventType=xpp.getEventType();
        	String statepast="";
        	String statepres="";
        	int statetext=0;
        	String response="";
        	while(eventType!=XmlPullParser.END_DOCUMENT)
        	{
        		if(eventType==XmlPullParser.START_TAG)
        		{	statepres=xpp.getName();
        			if(statepast=="step"&&statepres=="duration")
        				statetext=1;
        			statepast=statepres;
        			
        				
        		}
        		if(eventType==XmlPullParser.TEXT&&statetext==1)
        			response="La duracion del trayecto son"+xpp.getText();
        		eventType=xpp.next();
        	}
        	return response;
        	}
      }



