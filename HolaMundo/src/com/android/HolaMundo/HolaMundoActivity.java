package com.android.HolaMundo;

import android.app.Activity;
import android.os.Bundle;

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
        
        //--
        // 	TextView tv = new TextView(this);
        	
        //	tv.setText("Hola DAM");
        //  setContentView(tv);      	
        //--

        setContentView(R.layout.main);
        
//      TextView tv = (TextView) findViewById(R.id.texto);
//      tv.setText("Hola DAM2");   
//      tv.setText( R.string.hola );   
        
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
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