package com.example.directorio;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static DataBase db;
	private ListView lista;
	public static ArrayList<Person>  contactos;
	public static MiAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contactos = new ArrayList<Person>();
		db = new DataBase(this,"directorio",null,1);
		adapter = new MiAdapter(MainActivity.this,contactos);
		lista = (ListView)findViewById(R.id.lv_directorio);		
		consultarBD();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@Override
	protected void onRestart()
	{
		super.onRestart();
		consultarBD();
	}
	
	public void agregarClick(View v)
	{
		Intent intent = new Intent(MainActivity.this, Agregar.class);
		startActivity(intent);
	}
	
	private void consultarBD()
	{		
		contactos = db.consultarTodo();
		if(contactos != null)
		{
			adapter.clear();
			adapter.addAll(contactos);
			adapter.notifyDataSetChanged();
			lista.setAdapter(adapter);			
		}
		db.close();
	}
}
