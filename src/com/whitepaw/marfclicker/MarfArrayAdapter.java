package com.whitepaw.marfclicker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MarfArrayAdapter extends ArrayAdapter<ShopItem> {
	private final Context context;
	private final int layoutResourceId;
	private final ShopItem values[];

	public MarfArrayAdapter(Context context, int layoutResourceId,
			ShopItem values[]) {
		super(context, layoutResourceId, values);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.values = values;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(layoutResourceId, parent, false);
		}

		Button buy = (Button) row.findViewById(R.id.buy);

		TextView description = (TextView) row.findViewById(R.id.shop_label);
		description.setText(values[position].getDescription());

		TextView boost = (TextView) row.findViewById(R.id.shop_boost);
		boost.setText("Boost: +" + values[position].getBoost());

		TextView price = (TextView) row.findViewById(R.id.shop_price);
		price.setText("Cost: -" + values[position].getPrice());
		
		buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MarfNumbers.buySomething(values[position]);
			}
		});
		
		if (MarfNumbers.getBank() >= values[position].getPrice()) {
			buy.setEnabled(true); // default is false
		}
		else
			buy.setEnabled(false);
		return row;
	}
}
