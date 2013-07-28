package com.cmpe137.StudentSocial;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccount extends Activity {
	
	Button bCreateNewAccount;
	EditText etCreateUsername;
	EditText etCreateInitialPassword;
	EditText etCreateSecondPassword;
	TextView tvLoginErrorMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);
		bCreateNewAccount = (Button) findViewById(R.id.bCreateNewAccount);
		etCreateUsername = (EditText) findViewById(R.id.etCreateUsername);
		etCreateInitialPassword = (EditText) findViewById(R.id.etCreateInitialPassword);
		etCreateSecondPassword = (EditText) findViewById(R.id.etCreateSecondPassword);
		tvLoginErrorMessage = (TextView) findViewById(R.id.tvLoginErrorMessage);
		
		bCreateNewAccount.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String createdUsername = etCreateUsername.getText().toString();
				String passwordInitial = etCreateInitialPassword.getText().toString();
				String passwordSecond = etCreateSecondPassword.getText().toString();
				etCreateUsername.setText("");
				etCreateInitialPassword.setText("");
				etCreateSecondPassword.setText("");
				
				//Passwords do not match
				if(!passwordInitial.equals(passwordSecond)) {
					tvLoginErrorMessage.setText("Passwords do not match! Try Again.");
				}
				
				else {
					DatabaseHandler entry = new DatabaseHandler(CreateAccount.this);
					List<String> listOfUsernames;
					
					entry.open();
					listOfUsernames = entry.getAllUsernames();
					entry.close();
					
					boolean notTaken = true;
					for(int i = 0; i < listOfUsernames.size(); i++) {
						if(createdUsername.equalsIgnoreCase(listOfUsernames.get(i))) {
							tvLoginErrorMessage.setText("Username already taken!");
							i = listOfUsernames.size()-1;
							notTaken = false;
						}
					}
					if(notTaken) {
						entry.open();
						entry.addUserToDatabase(createdUsername, passwordInitial);
						entry.close();
						tvLoginErrorMessage.setText("Username created!");
					}
				}
				
				
				
				
				
			}
		});
	}
	
	

}
