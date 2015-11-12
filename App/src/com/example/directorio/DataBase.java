package com.example.directorio;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper
{

	public DataBase(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE contactos(id INTEGER PRIMARY KEY, nombre TEXT, telefono TEXT)");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS contactos");
		db.execSQL("CREATE TABLE contactos(id INTEGER PRIMARY KEY, nombre TEXT, telefono TEXT)");
		
	}
	
	public void agregarContacto(Context context, Person p)
	{
		SQLiteDatabase bd = this.getWritableDatabase();
		ContentValues vals = new ContentValues();
		vals.put("nombre", p.name);
		vals.put("telefono", p.phone);
		bd.insert("contactos", null, vals);
		bd.close();
	}
	
	public Person buscarContacto(Context context, String busqueda)
	{
		Person res = new Person();
		SQLiteDatabase bd = this.getWritableDatabase();
		Cursor fila = bd.rawQuery("SELECT * FROM contactos WHERE nombre LIKE '%"+busqueda+"%'", null);
		if(fila.moveToFirst())
		{
			do
			{
				res.name = fila.getString(1);
				res.phone = fila.getString(2);
			}
			while(fila.moveToNext());
		}
		else
		{
			bd.close();
			return null;		
		}
		bd.close();
		return res;
	}
	
	public ArrayList<Person> consultarTodo()
	{
		ArrayList<Person> todos = new ArrayList<Person>();
		SQLiteDatabase bd = this.getWritableDatabase();
		Cursor cursor = bd.rawQuery("SELECT * FROM contactos", null);
		if(cursor.moveToFirst())
		{
			do
			{
				Person registro = new Person(cursor.getString(1), cursor.getString(2));
				todos.add(registro);
			}
			while(cursor.moveToNext());
		}
		else
		{
			bd.close();
			return null;
		}
		bd.close();
		return todos;
	}


}
