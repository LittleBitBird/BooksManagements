package com.example.booksmanagementsystem;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//查询图书的ListView适配器
public class BookSearchAdapter extends BaseAdapter {
	Context context;
	Book[] book;

	public BookSearchAdapter(Context c, Book[] b) {
		context = c;
		book = b;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return book.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	// 优化ListView
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		valueHolder value;
		if (convertView == null) {
			value = new valueHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.booksearch_list, null);
			value.mTextView1 = (TextView) convertView.findViewById(R.id.BookList1);
			value.mTextView2 = (TextView) convertView.findViewById(R.id.BookList2);
			value.mTextView3 = (TextView) convertView.findViewById(R.id.BookList3);
			value.mTextView4 = (TextView) convertView.findViewById(R.id.BookList4);
			value.mTextView5 = (TextView) convertView.findViewById(R.id.BookList5);
			convertView.setTag(value);
		} else {
			value = (valueHolder) convertView.getTag();
		}		
		String ID = book[position].getBookID() + "";
		if (ID.length() < 4) {
			int i=4-ID.length();
			for(int j=0;j<i;j++){
				ID="0"+ID;
			}
		}
		value.mTextView1.setText(ID);
		value.mTextView2.setText(book[position].getBookname());
		value.mTextView3.setText(book[position].getAuthor());
		value.mTextView4.setText(book[position].getPrice() + "");
		value.mTextView5.setText(book[position].getStatus() + "");
		return convertView;
	}

}

// 元素的缓冲类,用于优化ListView
class valueHolder {
	public TextView mTextView1;
	public TextView mTextView2;
	public TextView mTextView3;
	public TextView mTextView4;
	public TextView mTextView5;

}
