package com.example.booksmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Book_Delete_Activity extends Activity {
	DBAdapter DB;// 数据库应用对象
	private TextView mTextreturn;// 左上返回字样
	private TextView introduction;// 右上作品简介
	EditText edtBookID;// 用户输入的id
	TextView BookName, BookPrice, Author;// 用来显示的对应ID数据库中具体数据的区域
	Button sure;// 输入ID后确认ID对应的书的内容的button;
	Button btnDelete, btnReSet;
	TextView mMessage;// 靠近底部的信息提示textview
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// 底部四个跳转textview

	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book__delete_);
		edtBookID = (EditText) findViewById(R.id.edtBookID);
		BookName = (TextView) findViewById(R.id.TxtBookName);
		BookPrice = (TextView) findViewById(R.id.TxtBookPrice);
		Author = (TextView) findViewById(R.id.TxtAuthor);
		sure = (Button) findViewById(R.id.sure);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnReSet = (Button) findViewById(R.id.ReSet);
		txtSeatch = (TextView) findViewById(R.id.bookSearch);
		txtInsert = (TextView) findViewById(R.id.bookInsert);
		txtUpdate = (TextView) findViewById(R.id.bookUpdate);
		mMessage = (TextView) findViewById(R.id.Message);
		introduction = (TextView) findViewById(R.id.introduction);

		DB = new DBAdapter(Book_Delete_Activity.this);

		sure.setOnClickListener(SureAdapter);//// 单击"确定"button的事件
		btnDelete.setOnClickListener(DeleteButton);// 单击“删除”button事件
		btnReSet.setOnClickListener(ReSetButton);// 单击“重置”button事件
		txtSeatch.setOnClickListener(SearchActivity);
		txtInsert.setOnClickListener(InsertActivity);
		txtUpdate.setOnClickListener(UpdateActivity);
		introduction.setOnClickListener(Introduction);
		getIntentValue();
	}

	public void getIntentValue() {
		Intent i = getIntent();
		String message = i.getStringExtra("message");
		mMessage.setText("上一个页面为" + message + "页面");
	}

	// 单击简介所显示的菜单信息
	OnClickListener Introduction = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PopupMenu popup = new PopupMenu(Book_Delete_Activity.this, v); // 建立PopupMenu对象
			popup.getMenuInflater().inflate(R.menu.introduction, popup.getMenu()); // 加载xml资源
			popup.show();
		}
	};

	// 单击“删除”button事件
	OnClickListener DeleteButton = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = edtBookID.getText().toString().trim();
				DB.BookDeleteById(ID);
				mMessage.setText("该条记录成功删除");
			} catch (Exception e) {
				mMessage.setText("该条记录删除失败，请检查ID正确性");
			}
			DB.close();
		}
	};

	// 单击“重置”button事件
	OnClickListener ReSetButton = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edtBookID.setText(null);
			BookName.setText(null);
			BookPrice.setText(null);
			Author.setText(null);
		}
	};

	// 单击"确定"button的事件
	OnClickListener SureAdapter = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = edtBookID.getText().toString();
				Book[] book = DB.getBookDateByID(ID);// 获得对应ID的那条记录
				Log.e("123", book.length + "");
				BookName.setText(book[0].getBookname());
				Author.setText(book[0].getAuthor());
				BookPrice.setText(book[0].getPrice() + "");

			} catch (Exception e) {
				mMessage.setText("该条记录已被删除，并不存在");
			}
			DB.close();
		}
	};

	// 单击底部"查询"button的事件
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "删除");
			startActivity(i);
		}
	};

	// 单击底部"添加"字样的效果
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "删除");
			startActivity(i);
		}
	};

	// 单击底部"更新"字样的效果
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "删除");
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
			Intent i1 = new Intent(Book_Delete_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "删除");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Delete_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "删除");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Delete_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "删除");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Delete_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "删除");
			startActivity(i4);
			break;
		case ITEM5:
			Intent i = new Intent(Book_Delete_Activity.this, Login.class);
			startActivity(i);
			break;

		}
		return true;
	}
}
