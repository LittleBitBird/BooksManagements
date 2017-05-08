package com.example.booksmanagementsystem;

import android.R.integer;
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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Book_Search_Activity extends Activity {
	DBAdapter DB;// 数据库应用对象
	Button bt1, bt2, bt3;// IDsearch NameSearch alldate
	private TextView mTextreturn;// 左上返回字样
	private TextView introduction;// 右上作品简介
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// 底部四个跳转textview
	EditText et1, et2;// 输入bookID和BookName的区域
	ListView lv1;
	private TextView mMessage;// 显示信息 错误 或者成功
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book__search_);
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		bt3 = (Button) findViewById(R.id.button3);
		mTextreturn = (TextView) findViewById(R.id.Textreturn);
		introduction = (TextView) findViewById(R.id.introduction);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		lv1 = (ListView) findViewById(R.id.listView1);
		DB = new DBAdapter(Book_Search_Activity.this);
		mMessage = (TextView) findViewById(R.id.Message);
		txtInsert = (TextView) findViewById(R.id.bookInsert);
		txtDelete = (TextView) findViewById(R.id.bookDelete);
		txtUpdate = (TextView) findViewById(R.id.bookUpdate);
		
		
		bt1.setOnClickListener(IDSearch);
		bt2.setOnClickListener(NameSearch);
		bt3.setOnClickListener(AllDateSearch);
		txtInsert.setOnClickListener(InsertActivity);// 跳转插入页面
		txtDelete.setOnClickListener(DeleteActivity);
		txtUpdate.setOnClickListener(UpdateActivity);
		introduction.setOnClickListener(Introduction);
		
		getIntentValue();
	}
	
	public void getIntentValue() {
		try {
			Intent i = getIntent();
			String message = i.getStringExtra("message");
			mMessage.setText(message);
			if (message == null) {
				mMessage.setText("此为初始页面");
			} else {
				mMessage.setText("上一个页面为"+message+"页面");
			}
		} catch (Exception e) {
			mMessage.setText("此为初始页面");
		}
	}
	// 单击简介所显示的菜单信息
		OnClickListener Introduction = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(Book_Search_Activity.this, v); // 建立PopupMenu对象
				popup.getMenuInflater().inflate(R.menu.introduction,popup.getMenu());	//加载xml资源		
				popup.show();
			}
		};

	// 单击底部"更新"字样的效果
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "查询");
			startActivity(i);
		}
	};

	// 单击底部“删除”字样的效果
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "查询");
			startActivity(i);
		}
	};

	// 单击底部"添加"字样的效果
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "查询");
			startActivity(i);
		}
	};

	// 查询所有数据
	OnClickListener AllDateSearch = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				Book book[];
				book = DB.getAllBookDate();
				BookSearchAdapter BSA = new BookSearchAdapter(Book_Search_Activity.this, book);
				lv1.setAdapter(BSA);
			} catch (Exception e) {
				mMessage.setText("该书名不存在");
			}
			DB.close();
		}
	};

	// 按ID查询数据
	OnClickListener IDSearch = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = et1.getText().toString();
				Book[] book1 = DB.getBookDateByID(ID);
				BookSearchAdapter BSA = new BookSearchAdapter(Book_Search_Activity.this, book1);
				lv1.setAdapter(BSA);
			} catch (Exception e) {
				mMessage.setText("该ID不存在");
			}

			DB.close();
		}
	};

	// 按BookName查询数据
	OnClickListener NameSearch = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				Book book[] = DB.getAllBookDate();
				String BookName = et2.getText().toString();
				int size = book.length;
				Book book1[] = new Book[size];
				size = 0;
				for (int i = 0; i < book.length; i++) {
					if (book[i].getBookname().equals(BookName)) {
						book1[size] = new Book();
						book1[size] = book[i];
						Log.e("" + BookName, book[i].toString());
						Log.e("1" + BookName, book[size].toString());
						size++;
					}
				}
				Log.d("size", size + "");
				Book book2[] = new Book[size];
				for (int i = 0; i < size; i++) {
					book2[i] = book1[i];
				}
				Log.d("size", book2.length + "");
				BookSearchAdapter BSA = new BookSearchAdapter(Book_Search_Activity.this, book2);
				lv1.setAdapter(BSA);
			} catch (Exception e) {
				mMessage.setText("该书名不存在");
			}
			DB.close();
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
			Intent i1 = new Intent(Book_Search_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "查询");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Search_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "查询");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Search_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "查询");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Search_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "查询");
			startActivity(i4);
			break;
		case ITEM5:
			Intent i = new Intent(Book_Search_Activity.this, Login.class);			
			startActivity(i);
			break;
		
		}
		return true;
	}
}
