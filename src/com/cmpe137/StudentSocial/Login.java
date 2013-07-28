package com.cmpe137.StudentSocial;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	
	private EditText etLoginUsername;
	private EditText etLoginPassword;
	private Button bCreateAccount;
	private Button bLogin;
	private TextView tvLoginPageErrorMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		
		etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
		etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
		bCreateAccount = (Button) findViewById(R.id.bCreateAccount);
		bLogin = (Button) findViewById(R.id.bLogin);
		tvLoginPageErrorMessage = (TextView) findViewById(R.id.tvLoginPageErrorMessage);
		
		bCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class openedClass = Class.forName("com.cmpe137.StudentSocial.CreateAccount");
					Intent openedIntent = new Intent(Login.this, openedClass);
					startActivity(openedIntent);
					
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		bLogin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = etLoginUsername.getText().toString();
				String password = etLoginPassword.getText().toString();
				etLoginUsername.setText("");
				etLoginPassword.setText("");
				boolean logedIn = false;
				boolean usernameInList = false;
				
				DatabaseHandler entry = new DatabaseHandler(Login.this);
	
				//Checks to see if username is even in table
				List<String> listOfUsernames;
				entry.open();
				listOfUsernames = entry.getAllUsernames();
				entry.close();
				
				for(int i = 0; i < listOfUsernames.size(); i++) {
					String currentUsername = listOfUsernames.get(i);
					if(username.equalsIgnoreCase(currentUsername)) {
						usernameInList = true;
						i = listOfUsernames.size() -1;
					}
				}
				
				
				if(usernameInList) {
					entry.open();
					logedIn = entry.isUserAuthenticated(username, password);
					entry.close();
				}
					
				if(logedIn) {
					try{
						Person personLoggingIn = new Person(username);
						Bundle b = new Bundle();
						b.putSerializable("name", personLoggingIn);
						Class openedClass = Class.forName("com.cmpe137.StudentSocial.Menu");
						Intent openedIntent = new Intent(Login.this, openedClass);
						openedIntent.putExtras(b);
						startActivity(openedIntent);
						
					}
					catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				}
				else {
					tvLoginPageErrorMessage.setText("Login Failed. Try Again!");
					
				}
				
				if(!usernameInList) {
					tvLoginPageErrorMessage.setText("Username Not Found!");
				}
				
				
				
			}
		});
	}

}
