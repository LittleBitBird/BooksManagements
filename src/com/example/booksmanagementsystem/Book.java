package com.example.booksmanagementsystem;

public class Book {
	// 创建BOOK对象对应bookinfo表
	public int bookID = -1;
	public String bookname;
	public String author;
	public float price;
	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public String toString() {
		// TODO Auto-generated method stub
		String result = "";
		result += "BookId:" + this.bookID + ",";
		result += "书名:" + this.bookname + ",";
		result += "作者:" + this.author + ",";
		result += "价格:" + this.price + ",";
		result += "状态:" + this.status;
		return result;
	}
}
