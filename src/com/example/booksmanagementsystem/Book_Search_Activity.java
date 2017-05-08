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
	DBAdapter DB;// ���ݿ�Ӧ�ö���
	Button bt1, bt2, bt3;// IDsearch NameSearch alldate
	private TextView mTextreturn;// ���Ϸ�������
	private TextView introduction;// ������Ʒ���
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// �ײ��ĸ���תtextview
	EditText et1, et2;// ����bookID��BookName������
	ListView lv1;
	private TextView mMessage;// ��ʾ��Ϣ ���� ���߳ɹ�
	
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
		txtInsert.setOnClickListener(InsertActivity);// ��ת����ҳ��
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
				mMessage.setText("��Ϊ��ʼҳ��");
			} else {
				mMessage.setText("��һ��ҳ��Ϊ"+message+"ҳ��");
			}
		} catch (Exception e) {
			mMessage.setText("��Ϊ��ʼҳ��");
		}
	}
	// �����������ʾ�Ĳ˵���Ϣ
		OnClickListener Introduction = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(Book_Search_Activity.this, v); // ����PopupMenu����
				popup.getMenuInflater().inflate(R.menu.introduction,popup.getMenu());	//����xml��Դ		
				popup.show();
			}
		};

	// �����ײ�"����"������Ч��
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "��ѯ");
			startActivity(i);
		}
	};

	// �����ײ���ɾ����������Ч��
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "��ѯ");
			startActivity(i);
		}
	};

	// �����ײ�"���"������Ч��
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Search_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "��ѯ");
			startActivity(i);
		}
	};

	// ��ѯ��������
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
				mMessage.setText("������������");
			}
			DB.close();
		}
	};

	// ��ID��ѯ����
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
				mMessage.setText("��ID������");
			}

			DB.close();
		}
	};

	// ��BookName��ѯ����
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
				mMessage.setText("������������");
			}
			DB.close();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, Menu.FIRST, 0, "��ѯ");
		menu.add(1, Menu.FIRST + 1, 0, "ɾ��");
		menu.add(1, Menu.FIRST + 2, 0, "����");
		menu.add(1, Menu.FIRST + 3, 0, "���");
		menu.add(1, Menu.FIRST + 4, 0, "ע��");

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
			i1.putExtra("message", "��ѯ");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Search_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "��ѯ");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Search_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "��ѯ");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Search_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "��ѯ");
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
