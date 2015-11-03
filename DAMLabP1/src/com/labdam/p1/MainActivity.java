
package com.labdam.p1;

import com.labdam.p1.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.view.View;
import android.net.Uri;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.com.labdam.p2.MESSAGE";

    private CheckBox chkCrema;
    private CheckBox chkAzucar;
   
    private RadioButton cafeDescafeinado;
    private RadioButton cafeExpresso;
    private RadioButton cafeSolidario;

    private Button btnBuscar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    chkCrema         = (CheckBox) findViewById(R.id.chkCrema);
	    chkAzucar        = (CheckBox) findViewById(R.id.chkAzucar);
		
	    cafeDescafeinado = (RadioButton) findViewById(R.id.cafeDescafeinado);
	    cafeExpresso     = (RadioButton) findViewById(R.id.cafeExpresso);
	    cafeSolidario    = (RadioButton) findViewById(R.id.cafeSolidario);

	    btnBuscar = (Button) findViewById(R.id.btnBuscar);

		btnBuscar.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
					String myUriString = "https://maps.googleapis.com/maps/api/directions/xml?origin=place_id:ChIJNbSXHCYmQg0RUhUT2ytPcTM&destination=place_id:ChIJz7ZjFH4oQg0RYS0zuSBBoxQ&key=AIzaSyAmNs57YsWmkZPO3CCfPzYvtmFsY1BNQtY";
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myUriString));
					startActivity(intent);			
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

}

