package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PostStatus extends Activity {
	
	Button bPostStatus;
	EditText etStatus;
	TextView tvStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poststatus);
		bPostStatus = (Button) findViewById(R.id.bPostStatus);
		etStatus = (EditText) findViewById(R.id.etStatus);
		tvStatus = (TextView) findViewById(R.id.tvStatus);
		
		bPostStatus.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
					String status = etStatus.getText().toString();
					etStatus.setText("");
					
					boolean worked = true;
					try{
					DatabaseHandler entry = new DatabaseHandler(PostStatus.this);
					entry.open();
					entry.addStatusToDatabase(status);
					entry.close();
					}
					catch (Exception e) {
						worked = false;
					}
					
					
					
				};
		});
	}

}
