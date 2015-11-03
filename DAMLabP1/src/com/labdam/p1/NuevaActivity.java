package com.labdam.p1;

import com.labdam.p1.R;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.content.Intent;

public class NuevaActivity extends Activity {

	TextView mensaje;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva);
		
        mensaje = (TextView)findViewById(R.id.textViewNueva);

        // Get the message from the intent
        Intent intent = getIntent();
        String mens  = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mensaje.setText( mens );
	}

}
