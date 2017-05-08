package com.example.booksmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Manager_center extends Activity {
	Button bt1, bt2;
	TextView tv1;
	DBAdapter DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_center);
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		tv1 = (TextView) findViewById(R.id.textView1);
		DB = new DBAdapter(Manager_center.this);
		DB.open();
		bt1.setOnClickListener(insertBookdate);
		bt2.setOnClickListener(QueryBookdate);
	}

	// 插入Book数据的单击相应按钮
	OnClickListener insertBookdate = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.BookInsert();
		}
	};

	// 查询Book数据的单击相应按钮
	OnClickListener QueryBookdate = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Book book[] = DB.getAllBookDate();
			int i = book.length;
			tv1.setText(book[i - 1].toString() + " " + i);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager_center, menu);
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
