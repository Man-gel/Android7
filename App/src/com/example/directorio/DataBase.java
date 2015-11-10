package com.example.directorio;

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
	
	public void agregar(Context context, String id)
	{
		SQLiteDatabase bd = this.getWritableDatabase();
		Cursor fila = bd.rawQuery("SELECT nombre,descripcion,imagen,video FROM jugadores WHERE id="+id, null);
		if(fila.moveToFirst()){
			jugador.id= Integer.parseInt(id);
			jugador.nombre=fila.getString(0);
			jugador.descripcion=fila.getString(1);
			jugador.imagen=fila.getString(2);
			jugador.video=fila.getString(3);
		}
		bd.close();
	}
	
	public void buscar(Context context, String busqueda){
		SQLiteDatabase bd = this.getWritableDatabase();
		Cursor fila = bd.rawQuery("SELECT nombre,descripcion,imagen,video,id FROM jugadores WHERE nombre LIKE '%"+busqueda+"%'", null);
		if(fila.moveToFirst()){
			jugador.id= Integer.parseInt(fila.getString(4));
			jugador.nombre=fila.getString(0);
			jugador.descripcion=fila.getString(1);
			jugador.imagen=fila.getString(2);
			jugador.video=fila.getString(3);
		}
		bd.close();
	}


}
