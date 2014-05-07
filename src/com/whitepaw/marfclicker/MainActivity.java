package com.whitepaw.marfclicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView mBank = (TextView) findViewById(R.id.bank);
		final ImageView mHusky = (ImageView) findViewById(R.id.husky);
		final TextView mIncome = (TextView) findViewById(R.id.income);
		final TextView mAdvert = (TextView) findViewById(R.id.advert);
		
		mBank.setText(MarfNumbers.getBankString());
		mIncome.setText(MarfNumbers.getIncomeString());

		mHusky.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mHusky.setScaleX(1.2f);
					mHusky.setScaleY(1.2f);
					MarfNumbers.addToBank(1);
					mBank.setText(MarfNumbers.getBankString());
					break;
				case MotionEvent.ACTION_UP:
					mHusky.setScaleX(1.0f);
					mHusky.setScaleY(1.0f);
					break;
				}
				return true;
			}
		});
		
		mAdvert.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getApplicationContext(), "Reset Desu", Toast.LENGTH_SHORT).show();
				mHusky.setScaleX(1.0f);
				mHusky.setScaleY(1.0f);
				MarfNumbers.setAlltime(0);
				MarfNumbers.setBank(0);
				MarfNumbers.setIncome(0);
				mBank.setText(MarfNumbers.getBankString());
				mIncome.setText(MarfNumbers.getIncomeString());
				return false;
			}
		});
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

}
