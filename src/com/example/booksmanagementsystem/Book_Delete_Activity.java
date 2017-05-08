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
	DBAdapter DB;// ���ݿ�Ӧ�ö���
	private TextView mTextreturn;// ���Ϸ�������
	private TextView introduction;// ������Ʒ���
	EditText edtBookID;// �û������id
	TextView BookName, BookPrice, Author;// ������ʾ�Ķ�ӦID���ݿ��о������ݵ�����
	Button sure;// ����ID��ȷ��ID��Ӧ��������ݵ�button;
	Button btnDelete, btnReSet;
	TextView mMessage;// �����ײ�����Ϣ��ʾtextview
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// �ײ��ĸ���תtextview

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

		sure.setOnClickListener(SureAdapter);//// ����"ȷ��"button���¼�
		btnDelete.setOnClickListener(DeleteButton);// ������ɾ����button�¼�
		btnReSet.setOnClickListener(ReSetButton);// ���������á�button�¼�
		txtSeatch.setOnClickListener(SearchActivity);
		txtInsert.setOnClickListener(InsertActivity);
		txtUpdate.setOnClickListener(UpdateActivity);
		introduction.setOnClickListener(Introduction);
		getIntentValue();
	}

	public void getIntentValue() {
		Intent i = getIntent();
		String message = i.getStringExtra("message");
		mMessage.setText("��һ��ҳ��Ϊ" + message + "ҳ��");
	}

	// �����������ʾ�Ĳ˵���Ϣ
	OnClickListener Introduction = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PopupMenu popup = new PopupMenu(Book_Delete_Activity.this, v); // ����PopupMenu����
			popup.getMenuInflater().inflate(R.menu.introduction, popup.getMenu()); // ����xml��Դ
			popup.show();
		}
	};

	// ������ɾ����button�¼�
	OnClickListener DeleteButton = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = edtBookID.getText().toString().trim();
				DB.BookDeleteById(ID);
				mMessage.setText("������¼�ɹ�ɾ��");
			} catch (Exception e) {
				mMessage.setText("������¼ɾ��ʧ�ܣ�����ID��ȷ��");
			}
			DB.close();
		}
	};

	// ���������á�button�¼�
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

	// ����"ȷ��"button���¼�
	OnClickListener SureAdapter = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = edtBookID.getText().toString();
				Book[] book = DB.getBookDateByID(ID);// ��ö�ӦID��������¼
				Log.e("123", book.length + "");
				BookName.setText(book[0].getBookname());
				Author.setText(book[0].getAuthor());
				BookPrice.setText(book[0].getPrice() + "");

			} catch (Exception e) {
				mMessage.setText("������¼�ѱ�ɾ������������");
			}
			DB.close();
		}
	};

	// �����ײ�"��ѯ"button���¼�
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "ɾ��");
			startActivity(i);
		}
	};

	// �����ײ�"���"������Ч��
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "ɾ��");
			startActivity(i);
		}
	};

	// �����ײ�"����"������Ч��
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Delete_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "ɾ��");
			startActivity(i);
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
			Intent i1 = new Intent(Book_Delete_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "ɾ��");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Delete_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "ɾ��");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Delete_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "ɾ��");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Delete_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "ɾ��");
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
