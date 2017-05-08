package com.example.booksmanagementsystem;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.*;

public class Book_Update_Activity extends Activity {
	private TextView mTextreturn;// 左上返回字样
	private TextView introduction;// 右上作品简介
	private EditText mEdtBookID;// 输入图书id
	private Button mSure;// 确认图书id并显示信息如果没有信息messge显示错误
	private EditText mEdtBookName;// 输入书名
	private EditText mEdtAuthor;// 输入作者
	private EditText mEdtPrice;// 输入价格
	private Button mBtnUpdate;// 删除
	private Button mReSet;// 重置
	private TextView mMessage;// 显示信息 错误 或者成功
	private TextView mBookSearch;// 底部 查询 删除 更新 添加 跳转页
	private TextView mBookDelete;
	private TextView mBookUpdate;
	private TextView mBookInsert;
	DBAdapter DB; // 数据库应用对象

	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book__update_);
		mTextreturn = (TextView) findViewById(R.id.Textreturn);// 左上返回字样
		introduction = (TextView) findViewById(R.id.introduction);
		mEdtBookID = (EditText) findViewById(R.id.edtBookID);// 输入图书id
		mSure = (Button) findViewById(R.id.sure);// 确认图书id并显示信息如果没有信息messge显示错误
		mEdtBookName = (EditText) findViewById(R.id.edtBookName);// 输入书名
		mEdtAuthor = (EditText) findViewById(R.id.edtAuthor);// 输入作者
		mEdtPrice = (EditText) findViewById(R.id.edtPrice);// 输入价格
		mBtnUpdate = (Button) findViewById(R.id.btnUpdate);// 更新
		mReSet = (Button) findViewById(R.id.ReSet);// 重置
		mMessage = (TextView) findViewById(R.id.Message);// 显示信息 错误 或者成功
		mBookSearch = (TextView) findViewById(R.id.bookSearch);// 底部 查询 删除 更新 添加
																// 跳转页
		mBookDelete = (TextView) findViewById(R.id.bookDelete);
		mBookUpdate = (TextView) findViewById(R.id.bookUpdate);
		mBookInsert = (TextView) findViewById(R.id.bookInsert);

		DB = new DBAdapter(Book_Update_Activity.this);// 建立数据库对象

		mBookSearch.setOnClickListener(SearchActivity);
		mBookDelete.setOnClickListener(DeleteActivity);
		mBookInsert.setOnClickListener(InsertActivity);

		getIntentValue();
		mSure.setOnClickListener(msure);
		mBtnUpdate.setOnClickListener(UpdateEvent);
		introduction.setOnClickListener(Introduction);

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
			PopupMenu popup = new PopupMenu(Book_Update_Activity.this, v); // 建立PopupMenu对象
			popup.getMenuInflater().inflate(R.menu.introduction, popup.getMenu()); // 加载xml资源
			popup.show();
		}
	};

	// 单击“更新”按钮发生的事件
	OnClickListener UpdateEvent = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				int ID = Integer.parseInt(mEdtBookID.getText().toString());
				String BookName = mEdtBookName.getText().toString();
				String Author = mEdtAuthor.getText().toString();
				float Price = Float.parseFloat(mEdtPrice.getText().toString());
				DB.UpdateBookDate(ID, BookName, Author, Price);
				mMessage.setText("该条记录更新成功");
			} catch (Exception e) {
				mMessage.setText("记录更新失败，请认真检查输入信息，图书价格只能为数字");
			}
			DB.close();
		}
	};

	// 单击“确定”按钮发生的事件
	OnClickListener msure = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = mEdtBookID.getText().toString();
				Book[] book = DB.getBookDateByID(ID);// 获得对应ID的那条记录
				Log.e("123", book.length + "");
				mEdtBookName.setText(book[0].getBookname());
				mEdtAuthor.setText(book[0].getAuthor());
				mEdtPrice.setText(book[0].getPrice() + "");
			} catch (Exception e) {
				mMessage.setText("该条记录并不存在");
			}
			DB.close();
		}
	};

	// 单击底部"查询"button的事件
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "更新");
			startActivity(i);
		}
	};

	// 单击底部“删除”字样的效果
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "更新");
			startActivity(i);
		}
	};

	// 单击底部"添加"字样的效果
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "更新");
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
			Intent i1 = new Intent(Book_Update_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "更新");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Update_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "更新");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Update_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "更新");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Update_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "更新");
			startActivity(i4);
			break;
		case ITEM5:
			Intent i = new Intent(Book_Update_Activity.this, Login.class);			
			startActivity(i);
			break;
		
		}
		return true;
	}
}
