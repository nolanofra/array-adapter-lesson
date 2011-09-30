package com.nolanofra.app.test.arayAdapterLesson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArrayAdapterLessonActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static String TAG = "ArrayAdapterLessonActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set layout
		setContentView(R.layout.main);

		ListView listView = (ListView) findViewById(R.id.arrayList);

		ArrayAdapter<CountryCapital> arrayAdapter = new ArrayAdapter<CountryCapital>(
				this, R.layout.list_row, R.id.countryLabel, createItems()) {
			
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent){	
				// Bad case
				//return getViewNoOptimized(position,convertView,parent);
				// lazy case
				//return getViewLazy(position,convertView,parent);
				// Holder View case
				
				
				return getViewHolder(position,convertView,parent);
			}

			public View getViewNoOptimized(int position, View convertView, ViewGroup parent) {

				Log.d(TAG, "getViewNoOptimized");
				
				CountryCapital item = getItem(position);

				LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowView = inflater.inflate(R.layout.list_row, null);

				TextView countryView = (TextView)rowView.findViewById(R.id.country);
				TextView capitalView = (TextView)rowView.findViewById(R.id.capital);

				countryView.setText(item.country);
				capitalView.setText(item.capital);

				return rowView;
			}
			
			public View getViewLazy(int position, View convertView, ViewGroup parent) {

				Log.d(TAG, "getViewLazy");
				
				if(convertView==null){

					Log.d(TAG, "convertView==null => inflating");
					LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = inflater.inflate(R.layout.list_row, null);					
				}

				CountryCapital item = getItem(position);				
				
				TextView countryView = (TextView)convertView.findViewById(R.id.country);
				TextView capitalView = (TextView)convertView.findViewById(R.id.capital);

				countryView.setText(item.country);
				capitalView.setText(item.capital);

				return convertView;
			}	
			
			public View getViewHolder(int position, View convertView, ViewGroup parent) {

				Log.d(TAG, "getViewHolder");
				ViewHolder viewHolder = null;
				
				if(convertView==null){

					Log.d(TAG, "convertView==null => inflating");
					LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = inflater.inflate(R.layout.list_row, null);

					viewHolder = new ViewHolder();

					viewHolder.countryView = (TextView)convertView.findViewById(R.id.country);
					viewHolder.capitalView = (TextView)convertView.findViewById(R.id.capital);

					convertView.setTag(viewHolder);
				}else{

					viewHolder = (ViewHolder)convertView.getTag();
				}

				CountryCapital item = getItem(position);

				viewHolder.countryView.setText(item.country);
				viewHolder.capitalView.setText(item.capital);

				return convertView;
			}			

		};

		listView.setAdapter(arrayAdapter);
	}

	private static class CountryCapital {
		public String capital;
		public String country;
		
		public CountryCapital(String _country, String _capital)
		{
			this.capital = _capital;
			this.country = _country;
		}
	}

	private CountryCapital[] createItems() {
		
		return new CountryCapital[]{ new CountryCapital("Italy", "Rome"), 
										new CountryCapital("Afghanistan", "Kabul"), 
										new CountryCapital("Albania", "Tirane"),
										new CountryCapital("Algeria", "Algiers"),
										new CountryCapital("Andorra", "Andorra La vella"),
										new CountryCapital("Austria", "Vienna"),
										new CountryCapital("Australia", "Canberra"),
										new CountryCapital("Belgium", "Brussels"),
										new CountryCapital("Bangladesh", "Dhaka"),
										new CountryCapital("BRazil", "Brasilia"),
										new CountryCapital("Canada", "Ottawa"),
										new CountryCapital("Geramany", "Berlin"),
										new CountryCapital("Finland", "Helsinki"),
										new CountryCapital("India", "New Delhi"),
										new CountryCapital("Iran", "Tehran"),
										new CountryCapital("Ireland", "Dublin"),
										new CountryCapital("Israel", "Jerusalem"),
										new CountryCapital("Latvia", "Riga"),
										new CountryCapital("Monaco", "Monaco"),
										new CountryCapital("Morocco", "Rabat"),
										new CountryCapital("Norway", "Oslo"),
										new CountryCapital("Poland", "Warsaw"),
										new CountryCapital("Peru", "Lima"),
										new CountryCapital("San Marino", "San Marino")										
										};				
	}
	
	private static class ViewHolder{
		public TextView capitalView;
		public TextView countryView;
	}
}