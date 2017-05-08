package com.example.booksmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	// Content View Elements

	private EditText mEdtId;
	private EditText mEdtPassword;
	private Button mLogin;
	private Button mQuit;
	TextView mMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mEdtId = (EditText) findViewById(R.id.edtId);
		mEdtPassword = (EditText) findViewById(R.id.edtPassword);
		mLogin = (Button) findViewById(R.id.login);
		mQuit = (Button) findViewById(R.id.quit);
		mMessage = (TextView) findViewById(R.id.Message);
		
		mLogin.setOnClickListener(login);
	}

	OnClickListener login = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mEdtId.getText().toString().equals("1410075040")
					&& mEdtPassword.getText().toString().equals("1410075040")) {
				Intent i = new Intent(Login.this, Book_Search_Activity.class);
				i.putExtra("message", "µ«¬º");
				startActivity(i);
			}else{
				mMessage.setText("’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
