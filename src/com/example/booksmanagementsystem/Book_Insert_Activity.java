package com.example.booksmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Book_Insert_Activity extends Activity {
	DBAdapter DB;// 数据库应用对象
	private TextView mTextreturn;// 左上返回字样
	private TextView introduction;// 右上作品简介
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// 底部四个跳转textview
	TextView mMessage;// 底部的提示信息；
	EditText edtBookName, edtAuthor, edtPrice;// 对应输入的书名，作者，价格
	Button btnInsert, btnReset; // 对应 “添加”“重置”两个按钮
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book__insert_);
		DB = new DBAdapter(Book_Insert_Activity.this);
		txtSeatch = (TextView) findViewById(R.id.bookSearch);// 底部"查询"textview字样
		txtDelete = (TextView) findViewById(R.id.bookDelete);
		txtUpdate = (TextView) findViewById(R.id.bookUpdate);
		edtBookName = (EditText) findViewById(R.id.detBookName);// 书名
		edtAuthor = (EditText) findViewById(R.id.detBookauthor);// 作者名
		edtPrice = (EditText) findViewById(R.id.detBookPirce);// 书的价格
		mMessage = (TextView) findViewById(R.id.Message);// 底部的提示信息
		btnInsert = (Button) findViewById(R.id.InsertBook);
		btnReset = (Button) findViewById(R.id.ReSet);
		introduction = (TextView) findViewById(R.id.introduction);
		
		btnInsert.setOnClickListener(InsertAdapter);
		btnReset.setOnClickListener(ReSetAdapter);
		txtSeatch.setOnClickListener(SearchActivity);
		txtDelete.setOnClickListener(DeleteActivity);
		txtUpdate.setOnClickListener(UpdateActivity);
		introduction.setOnClickListener(Introduction);
		getIntentValue();
	}
	
	public void getIntentValue() {	
			Intent i = getIntent();
			String message = i.getStringExtra("message");
			mMessage.setText("上一个页面为"+message+"页面");
	}
	
	// 单击简介所显示的菜单信息
		OnClickListener Introduction = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(Book_Insert_Activity.this, v); // 建立PopupMenu对象
				popup.getMenuInflater().inflate(R.menu.introduction,popup.getMenu());	//加载xml资源		
				popup.show();
			}
		};

	// 单击按钮"添加"所产生的效果
	OnClickListener InsertAdapter = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String BookName = edtBookName.getText().toString();
				String BookAuthor = edtAuthor.getText().toString();
				float BookPrice = Float.parseFloat(edtPrice.getText().toString());
				Log.e("float", BookPrice + "");
				DB.BookInsert1(BookName, BookAuthor, BookPrice);
				mMessage.setText("这条记录已经成功插入");
			} catch (Exception e) {
				mMessage.setText("纪律插入失败请认真检查输入信息，图书价格只能为数字");
			}
			DB.close();
		}
	};

	// 单击按钮"重置"所产生的效果
	OnClickListener ReSetAdapter = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edtBookName.setText(null);
			edtAuthor.setText(null);
			edtPrice.setText(null);
		}
	};

	// 单击底部"查询"字样的效果
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "添加");
			startActivity(i);
		}
	};

	// 单击底部“删除”字样的效果
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "添加");
			startActivity(i);
		}
	};

	// 单击底部"更新"字样的效果
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "添加");
			startActivity(i);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, Menu.FIRST, 0, "查询");
		menu.add(1, Menu.FIRST + 1, 0, "删除");
		menu.add(1, Menu.FIRST + 2, 0, "更新");
		menu.add(1, Menu.FIRST + 3, 0, "添加");
		menu.add(1, Menu.FIRST + 4, 0, "注销");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case ITEM1:
			Intent i1 = new Intent(Book_Insert_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "添加");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Insert_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "添加");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Insert_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "添加");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Insert_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "添加");
			startActivity(i4);
			break;
		case ITEM5:
			Intent i = new Intent(Book_Insert_Activity.this, Login.class);			
			startActivity(i);
			break;
		
		}
		return true;
	}
}
