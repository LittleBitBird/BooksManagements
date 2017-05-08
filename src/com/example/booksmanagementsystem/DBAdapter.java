package com.example.booksmanagementsystem;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBAdapter {
	private static final String DB_Name = "BooksManagement.db";
	private static final String DB_Book_Table = "Bookinfo";
	private static final int DB_version = 1;
	// Bookinfo�������
	private static final String book_id = "_id";
	private static final String book_name = "name";
	private static final String book_author = "author";
	private static final String book_price = "price";
	private static final String book_status = "status";

	private SQLiteDatabase db;
	private final Context context;
	DBOpenHelper dbopenHelper;
	private static final String db_delete = "DROP TABLE IF EXISTS " + DB_Book_Table;
	private static final String db_Book_create1 = "create table " + DB_Book_Table + "(" + book_id
			+ " integer primary key autoincrement, " + book_name + " text not null, " + book_author + " text, "
			+ book_price + " float, " + book_status + " text);";

	// ���췽��;
	public DBAdapter(Context c) {
		context = c;
	}

	// ���ڴ������ݿ����
	private static class DBOpenHelper extends SQLiteOpenHelper {
		// ����ͼ�����ݿ�
		private static final String db_Book_create = "create table " + DB_Book_Table + "(" + book_id
				+ " integer primary key autoincrement, " + book_name + " text not null, " + book_author + " text, "
				+ book_price + " float, " + book_status + " text);";

		public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(db_Book_create);
			Log.e("create", "create1");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABEL IF EXISTS" + DB_Book_Table);
			Log.e("update", "update1");
			onCreate(db);
		}
	}

	// ���ڴ��������ݿ�ķ���
	public void open() throws SQLiteException {
		dbopenHelper = new DBOpenHelper(context, DB_Name, null, DB_version);
		try {
			db = dbopenHelper.getWritableDatabase();
			Log.e("open", "open1");
		} catch (SQLiteException e) {
			db = dbopenHelper.getReadableDatabase();
			Log.e("open", "open2");
		}
	}

	// ���ڹر����ݿ�ķ���
	public void close() {

		if (db != null) {
			db.close();
			db = null;
		}
	}

	// ����ID��Bookinfo����ɾ�����ݵķ���
	public void BookDeleteById(String id) {
		db.delete(DB_Book_Table, book_id + " = ?", new String[] { id });
	}

	// ��Bookinfo����������ݵķ���
	public void BookInsert() {
		ContentValues value = new ContentValues();

		value.put(book_name, "�����뺣");
		value.put(book_author, "������");
		value.put(book_price, 23);
		value.put(book_status, "����");
		Log.e("1", "insert");
		db.insert(DB_Book_Table, null, value);
	}

	public void BookInsert1(String a, String b, float c) {
		ContentValues value = new ContentValues();

		value.put(book_name, a);
		value.put(book_author, b);
		value.put(book_price, c);
		value.put(book_status, "����");
		Log.e("1", "insert");
		db.insert(DB_Book_Table, null, value);
	}

	//// ��Bookinfo���в�ѯ���ݵķ���
	public Book[] getAllBookDate() {
		Cursor cursor = db.query(DB_Book_Table,
				new String[] { book_id, book_name, book_author, book_price, book_status }, null, null, null, null,
				null);
		Book[] book = getBookDateHelper(cursor);
		return book;
	}

	//// ͨ��ID��Bookinfo���в�ѯ���ݵķ���
	public Book[] getBookDateByID(String id) {
		Cursor cursor = db.query(DB_Book_Table,
				new String[] { book_id, book_name, book_author, book_price, book_status }, book_id + " = ?",
				new String[] { id }, null, null, null);
		Book book[] = getBookDateHelper(cursor);
		return book;
	}

	public Book[] getBookDateHelper(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		Book[] book = new Book[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			book[i] = new Book();
			book[i].setBookID(cursor.getInt(0));
			book[i].setBookname(cursor.getString(1));
			book[i].setAuthor(cursor.getString(2));
			book[i].setPrice(cursor.getFloat(3));
			book[i].setStatus(cursor.getString(4));
			Log.e(i + "", book[i].toString());
			cursor.moveToNext();
		}
		return book;
	}

	public void UpdateBookDate(int ID, String bookname, String author, float price) {

		String query = " update Bookinfo set name= ? ,author= ?,price= ? where _id= ?";
		db.execSQL(query, new Object[] { bookname, author, price, ID });
		// ContentValues update = new ContentValues();
		// update.put(book_name, bookname);
		// update.put(book_author, author);
		// update.put(book_price, price);
		//
		// db.update(DB_Book_Table, update,book_id+"="+ID , null);
	}

	public void deletetable() {
		Log.e("delete", "delete");
		db.execSQL(db_delete);
	}

	// ������
	public void createtable() {
		Log.e("create", "create1");
		db.execSQL(db_Book_create1);

	}
}
