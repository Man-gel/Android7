package com.example.directorio;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MiAdapter extends ArrayAdapter<Person>
{
	
	public MiAdapter(Context context,  ArrayList<Person> objects) {
		super(context, 0,  objects);
		
	}
	
	private static class ViewHolder
	{
		private TextView nameView;
		private TextView phoneView;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		ViewHolder viewHolder;
		Person registro = (Person)getItem(position);
		if(view == null)
		{
			view = LayoutInflater.from(getContext()).inflate(R.layout.row_list, parent,false);
			viewHolder = new ViewHolder();
			viewHolder.nameView = (TextView)view.findViewById(R.id.tv_name);
			viewHolder.phoneView = (TextView)view.findViewById(R.id.tv_phone);
			view.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)view.getTag();
		}
		if(registro != null)
		{
			viewHolder.nameView.setText(registro.name);
			viewHolder.phoneView.setText(registro.phone);
		}
		return view;		
	}

}
