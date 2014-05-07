package com.whitepaw.marfclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView mBank = null;
	private ImageView mHusky = null;
	private TextView mIncome = null;
	private TextView mAdvert = null;
	private TextView mTotal = null;

	private Context context = null;
	private SharedPreferences prefs = null;
	private Handler handler = null;
	private Runnable updateUI = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// handles for main views
		mBank = (TextView) findViewById(R.id.bank);
		mHusky = (ImageView) findViewById(R.id.husky);
		mIncome = (TextView) findViewById(R.id.income);
		mAdvert = (TextView) findViewById(R.id.advert);

		// handles for stats data column
		mTotal = (TextView) findViewById(R.id.stats_values_total);

		// other handles to important things
		context = getApplicationContext();
		prefs = getPreferences(Context.MODE_PRIVATE);
		
		
		handler = new Handler();
		updateUI = new Runnable() {
		    public void run() {
		        MarfNumbers.applyIncome();
		        updateFields();
		        handler.postDelayed(updateUI, 1000); // 1 second delay
		        }
		    };
		
		handler.post(updateUI);

		// main touch listener that powers the app
		mHusky.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					mHusky.setScaleX(1.2f);
					mHusky.setScaleY(1.2f);
					MarfNumbers.addToBank(1);
					updateFields();
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
				Toast.makeText(context, "Reset Desu", Toast.LENGTH_SHORT)
						.show();
				mHusky.setScaleX(1.0f);
				mHusky.setScaleY(1.0f);
				MarfNumbers.reset();
				updateFields();
				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}
	
	

	public void updateFields() {
		//temporary way of increasing income
		switch (MarfNumbers.getAlltime()) {
		case 100:
			MarfNumbers.increaseIncome(1);
			break;
		}

		mBank.setText(MarfNumbers.getBankString());
		mIncome.setText(MarfNumbers.getIncomeString());
		mTotal.setText(String.valueOf(MarfNumbers.getAlltime()));
	}

	public void saveData() {
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(getString(R.string.alltime), MarfNumbers.getAlltime());
		editor.putInt(getString(R.string.bank), MarfNumbers.getBank());
		editor.putInt(getString(R.string.income), MarfNumbers.getIncome());
		editor.commit();
	}

	public void loadData() {
		int alltime = prefs.getInt(getString(R.string.alltime), 0);
		int bank = prefs.getInt(getString(R.string.bank), 0);
		int income = prefs.getInt(getString(R.string.income), 0);

		MarfNumbers.setAlltime(alltime);
		MarfNumbers.setBank(bank);
		MarfNumbers.setIncome(income);

		updateFields();
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
		if (id == R.id.devname) {
			Toast.makeText(getApplicationContext(),
					"MarfClicker ©2014 Ethan Busbee", Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
