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
	private TextView mTextreturn;// ���Ϸ�������
	private TextView introduction;// ������Ʒ���
	private EditText mEdtBookID;// ����ͼ��id
	private Button mSure;// ȷ��ͼ��id����ʾ��Ϣ���û����Ϣmessge��ʾ����
	private EditText mEdtBookName;// ��������
	private EditText mEdtAuthor;// ��������
	private EditText mEdtPrice;// ����۸�
	private Button mBtnUpdate;// ɾ��
	private Button mReSet;// ����
	private TextView mMessage;// ��ʾ��Ϣ ���� ���߳ɹ�
	private TextView mBookSearch;// �ײ� ��ѯ ɾ�� ���� ��� ��תҳ
	private TextView mBookDelete;
	private TextView mBookUpdate;
	private TextView mBookInsert;
	DBAdapter DB; // ���ݿ�Ӧ�ö���

	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book__update_);
		mTextreturn = (TextView) findViewById(R.id.Textreturn);// ���Ϸ�������
		introduction = (TextView) findViewById(R.id.introduction);
		mEdtBookID = (EditText) findViewById(R.id.edtBookID);// ����ͼ��id
		mSure = (Button) findViewById(R.id.sure);// ȷ��ͼ��id����ʾ��Ϣ���û����Ϣmessge��ʾ����
		mEdtBookName = (EditText) findViewById(R.id.edtBookName);// ��������
		mEdtAuthor = (EditText) findViewById(R.id.edtAuthor);// ��������
		mEdtPrice = (EditText) findViewById(R.id.edtPrice);// ����۸�
		mBtnUpdate = (Button) findViewById(R.id.btnUpdate);// ����
		mReSet = (Button) findViewById(R.id.ReSet);// ����
		mMessage = (TextView) findViewById(R.id.Message);// ��ʾ��Ϣ ���� ���߳ɹ�
		mBookSearch = (TextView) findViewById(R.id.bookSearch);// �ײ� ��ѯ ɾ�� ���� ���
																// ��תҳ
		mBookDelete = (TextView) findViewById(R.id.bookDelete);
		mBookUpdate = (TextView) findViewById(R.id.bookUpdate);
		mBookInsert = (TextView) findViewById(R.id.bookInsert);

		DB = new DBAdapter(Book_Update_Activity.this);// �������ݿ����

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
		mMessage.setText("��һ��ҳ��Ϊ" + message + "ҳ��");
	}

	// �����������ʾ�Ĳ˵���Ϣ
	OnClickListener Introduction = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PopupMenu popup = new PopupMenu(Book_Update_Activity.this, v); // ����PopupMenu����
			popup.getMenuInflater().inflate(R.menu.introduction, popup.getMenu()); // ����xml��Դ
			popup.show();
		}
	};

	// ���������¡���ť�������¼�
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
				mMessage.setText("������¼���³ɹ�");
			} catch (Exception e) {
				mMessage.setText("��¼����ʧ�ܣ���������������Ϣ��ͼ��۸�ֻ��Ϊ����");
			}
			DB.close();
		}
	};

	// ������ȷ������ť�������¼�
	OnClickListener msure = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DB.open();
			try {
				String ID = mEdtBookID.getText().toString();
				Book[] book = DB.getBookDateByID(ID);// ��ö�ӦID��������¼
				Log.e("123", book.length + "");
				mEdtBookName.setText(book[0].getBookname());
				mEdtAuthor.setText(book[0].getAuthor());
				mEdtPrice.setText(book[0].getPrice() + "");
			} catch (Exception e) {
				mMessage.setText("������¼��������");
			}
			DB.close();
		}
	};

	// �����ײ�"��ѯ"button���¼�
	OnClickListener SearchActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Search_Activity.class);
			i.putExtra("message", "����");
			startActivity(i);
		}
	};

	// �����ײ���ɾ����������Ч��
	OnClickListener DeleteActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Delete_Activity.class);
			i.putExtra("message", "����");
			startActivity(i);
		}
	};

	// �����ײ�"���"������Ч��
	OnClickListener InsertActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Book_Update_Activity.this, Book_Insert_Activity.class);
			i.putExtra("message", "����");
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
			Intent i1 = new Intent(Book_Update_Activity.this, Book_Search_Activity.class);
			i1.putExtra("message", "����");
			startActivity(i1);
			break;
		case ITEM2:
			Intent i2 = new Intent(Book_Update_Activity.this, Book_Delete_Activity.class);
			i2.putExtra("message", "����");
			startActivity(i2);
			break;
		case ITEM3:
			Intent i3 = new Intent(Book_Update_Activity.this, Book_Update_Activity.class);
			i3.putExtra("message", "����");
			startActivity(i3);
			break;
		case ITEM4:
			Intent i4 = new Intent(Book_Update_Activity.this, Book_Insert_Activity.class);
			i4.putExtra("message", "����");
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
