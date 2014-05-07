package com.whitepaw.marfclicker;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MarfArrayAdapter extends ArrayAdapter<ShopItem> {
	private final Context context;
	private final ArrayList<ShopItem> values;
	
	public MarfArrayAdapter(Context context, ArrayList<ShopItem> values) {
		super(context, R.layout.shop_item, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.shop_item, parent, false);
		
		Button buy = (Button) rowView.findViewById(R.id.buy);
		
		TextView description = (TextView) rowView.findViewById(R.id.shop_label);
		description.setText(values.get(position).getDescription());
		
		TextView boost = (TextView) rowView.findViewById(R.id.shop_boost);
		boost.setText("Boost: +" + values.get(position).getBoost());
		
		TextView price = (TextView) rowView.findViewById(R.id.shop_price);
		price.setText("Cost: -" + values.get(position).getPrice());
		
		if(MarfNumbers.getBank() >= values.get(position).getPrice()) {
			buy.setEnabled(true);	//default is false
		}
		
		return rowView;
	}
}
