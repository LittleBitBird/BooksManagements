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
	DBAdapter DB;// ���ݿ�Ӧ�ö���
	private TextView mTextreturn;// ���Ϸ�������
	private TextView introduction;// ������Ʒ���
	TextView txtSeatch, txtDelete, txtUpdate, txtInsert;// �ײ��ĸ���תtextview
	TextView mMessage;// �ײ�����ʾ��Ϣ��
	EditText edtBookName, edtAuthor, edtPrice;// ��Ӧ��������������ߣ��۸�
	Button btnInsert, btnReset; // ��Ӧ ����ӡ������á�������ť
	
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
		txtSeatch = (TextView) findViewById(R.id.bookSearch);// �ײ�"��ѯ"textview����
		txtDelete = (TextView) findViewById(R.id.bookDelete);
		txtUpdate = (TextView) findViewById(R.id.bookUpdate);
		edtBookName = (EditText) findViewById(R.id.detBookName);// ����
		edtAuthor = (EditText) findViewById(R.id.detBookauthor);// ������
		edtPrice = (EditText) findViewById(R.id.detBookPirce);// ��ļ۸�
		mMessage = (TextView) findViewById(R.id.Message);// �ײ�����ʾ��Ϣ
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
			mMessage.setText("��һ��ҳ��Ϊ"+message+"ҳ��");
	}
	
	// �����������ʾ�Ĳ˵���Ϣ
		OnClickListener Introduction = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(Book_Insert_Activity.this, v); // ����PopupMenu����
				popup.getMenuInflater().inflate(R.menu.introduction,popup.getMenu());	//����xml��Դ		
				popup.show();
			}
		};

	// ������ť"���"��������Ч��
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
				mMessage.setText("������¼�Ѿ��ɹ�����");
			} catch (Exception e) {
				mMessage.setText("���ɲ���ʧ����������������Ϣ��ͼ��۸�ֻ��Ϊ����");
			}
			DB.close();
		}
	};

	// ������ť"����"��������Ч��
	OnClickListener ReSetAdapter = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edtBookName.setText(null);
			edtAuthor.setText(null);
			edtPrice.setText(null);
		}
	};

	// �����ײ�"��ѯ"������Ч��
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "���");
			startActivity(i);
		}
	};

	// �����ײ���ɾ����������Ч��
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "���");
			startActivity(i);
		}
	};

	// �����ײ�"����"������Ч��
	OnClickListener UpdateActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Insert_Activity.this, Book_Update_Activity.class);
			i.putExtra("message", "���");
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
			Intent i1 = new Intent(Book_Insert_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "���");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Insert_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "���");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Insert_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "���");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Insert_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "���");
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
