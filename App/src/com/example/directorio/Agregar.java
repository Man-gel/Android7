package com.example.directorio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends Activity {
	private EditText etNU_nombre;
	private EditText etNU_telefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar);
		etNU_nombre = (EditText)findViewById(R.id.et_new_nmb);
		etNU_telefono = (EditText)findViewById(R.id.et_new_ph);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar, menu);
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
	
	public void agregarNuevo(View v)
	{
		String nombre_nuevo = etNU_nombre.getText().toString().trim();
		String telefono_nuevo = etNU_telefono.getText().toString().trim();
		if(nombre_nuevo.isEmpty() || telefono_nuevo.isEmpty())
		{
			mostrarToast("Faltan campos por completar");
			return;
		}
		if(telefono_nuevo.matches(".*[!-/]+?([\\s]*|[0-9]+)"))
		{
			mostrarToast("No puede ingresar un número de telefono con signos o símbolos");
			etNU_telefono.setText("");
			return;
		}
		Person nuevo = new Person(nombre_nuevo,telefono_nuevo);
		Person enBD = MainActivity.db.buscarContacto(this, nombre_nuevo);
		if(enBD != null && nuevo.equals(enBD))
		{
			mostrarToast("No se pudo ingresar el nuevo contacto porque ya existe");
			etNU_telefono.setText("");
			etNU_nombre.requestFocus();
			return;
		}
		if( !MainActivity.db.agregarContacto(this, nuevo) )
			mostrarToast("No se pudo agregar el nuevo contacto");
		this.finish();
	}
	
	private void mostrarToast(CharSequence mensaje)
	{
		Context contexto = getApplicationContext();
		Toast toast = Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT);
		toast.setDuration(3);
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();	
	}
	
	public void cancelarClick(View v)
	{
		etNU_telefono.setText("");
		etNU_nombre.setText("");
		this.finish();
	}

}
