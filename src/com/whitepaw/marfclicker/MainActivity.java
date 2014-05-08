package com.whitepaw.marfclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView mBank = null;
	private ImageView mHusky = null;
	private TextView mIncome = null;
	private TextView mAdvert = null;

	private TextView mPuppies = null;
	private TextView mHuskies = null;
	private TextView mRoboskis = null;
	private TextView mBank_value = null;
	private TextView mTotal_value = null;

	private Button mShopButton = null;
	private DrawerLayout mShopDrawer = null;
	private ListView mShopListView = null;
	private MarfArrayAdapter adapter = null;
	private ShopItem values[] = null;

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

		// Setup Upgrade Shop Drawer, ListView, etc.
		mShopButton = (Button) findViewById(R.id.shop_button);
		mShopDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mShopListView = (ListView) findViewById(R.id.shop_content_listview);

		values = setupShopItems();
		adapter = new MarfArrayAdapter(this, R.layout.shop_item, values);
		mShopListView.setAdapter(adapter);

		// handles for stats data column
		mPuppies = (TextView) findViewById(R.id.stats_values_puppies);
		mHuskies = (TextView) findViewById(R.id.stats_values_huskies);
		mRoboskis = (TextView) findViewById(R.id.stats_values_roboskis);
		mBank_value = (TextView) findViewById(R.id.stats_values_banked);
		mTotal_value = (TextView) findViewById(R.id.stats_values_total);

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
					updateFields();
					break;
				}
				return true;
			}
		});

		mAdvert.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				reset();
				return true;
			}
		});

		mShopButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mShopDrawer.openDrawer(mShopListView);
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
		mBank.setText(MarfNumbers.getBankString());
		mIncome.setText(MarfNumbers.getIncomeString());

		mPuppies.setText(String.valueOf(MarfNumbers.getPuppies()));
		mHuskies.setText(String.valueOf(MarfNumbers.getHuskies()));
		mRoboskis.setText(String.valueOf(MarfNumbers.getRoboskis()));
		mBank_value.setText(String.valueOf(MarfNumbers.getBank()));
		mTotal_value.setText(String.valueOf(MarfNumbers.getAlltime()));

		adapter.notifyDataSetChanged();
	}

	public void reset() {
		mHusky.setScaleX(1.0f);
		mHusky.setScaleY(1.0f);
		for (int i = 0; i < values.length; i++) {
			values[i].reset();
		}
		MarfNumbers.reset();
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
		updateFields();
		shortToast("Reset Desu");
	}

	public void saveData() {
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(getString(R.string.alltime), MarfNumbers.getAlltime());
		editor.putInt(getString(R.string.bank), MarfNumbers.getBank());
		editor.putInt(getString(R.string.income), MarfNumbers.getIncome());
		editor.putInt(getString(R.string.puppies), MarfNumbers.getPuppies());
		editor.putInt(getString(R.string.puppiesPrice), values[0].getPrice());
		editor.putInt(getString(R.string.huskies), MarfNumbers.getHuskies());
		editor.putInt(getString(R.string.huskiesPrice), values[1].getPrice());
		editor.putInt(getString(R.string.roboskis), MarfNumbers.getRoboskis());
		editor.putInt(getString(R.string.roboskisPrice), values[2].getPrice());
		editor.commit();
	}

	public void loadData() {
		int alltime = prefs.getInt(getString(R.string.alltime), 0);
		int bank = prefs.getInt(getString(R.string.bank), 0);
		int income = prefs.getInt(getString(R.string.income), 0);
		int puppies = prefs.getInt(getString(R.string.puppies), 0);
		int puppiesPrice = prefs.getInt(getString(R.string.puppiesPrice), 10);
		int huskies = prefs.getInt(getString(R.string.huskies), 0);
		int huskiesPrice = prefs.getInt(getString(R.string.huskiesPrice), 100);
		int roboskis = prefs.getInt(getString(R.string.roboskis), 0);
		int roboskisPrice = prefs.getInt(getString(R.string.roboskisPrice),
				1000);

		if (puppiesPrice < 10)
			puppiesPrice = 10;
		if (huskiesPrice < 100)
			huskiesPrice = 100;
		if (roboskisPrice < 1000)
			roboskisPrice = 1000;

		MarfNumbers.setAlltime(alltime);
		MarfNumbers.setBank(bank);
		MarfNumbers.setIncome(income);
		MarfNumbers.setPuppies(puppies);
		values[0].setPrice(puppiesPrice);
		MarfNumbers.setHuskies(huskies);
		values[1].setPrice(huskiesPrice);
		MarfNumbers.setRoboskis(roboskis);
		values[2].setPrice(roboskisPrice);

		updateFields();
	}

	public void longToast(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public void shortToast(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.devname) {
			longToast("MarfClicker ©2014 Ethan Busbee");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public ShopItem[] setupShopItems() {
		ShopItem puppy = new ShopItem(ShopItem.classes.PUPPY);
		ShopItem husky = new ShopItem(ShopItem.classes.HUSKY);
		ShopItem roboski = new ShopItem(ShopItem.classes.ROBOSKI);
		ShopItem arr[] = new ShopItem[] { puppy, husky, roboski };
		return arr;
	}
}
