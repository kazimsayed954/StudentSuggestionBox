package com.kazim.studentsuggestionbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends MainActivity {
    TextView nameTv;
    TextView emailTv;
    Button logoutbtn,Suggestionbtn,AllSuggestionbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Home");
        context = this;
        init();
        setContentView(R.layout.activity_home);
        AllSuggestionbtn=findViewById(R.id.allSuggestion);
        Suggestionbtn=findViewById(R.id.suggestionbtn);
        Suggestionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,SuggestionActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        AllSuggestionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,AllSuggestionActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        //link views
        getViews();
    }

    private void getViews() {
        nameTv = findViewById(R.id.nameTv);
        nameTv.setText("Welcome "+sharedPreferences.getString("name","")+" to StudentSuggestionBox");
        emailTv = findViewById(R.id.emailTv);
        emailTv.setText(sharedPreferences.getString("email",""));
        logoutbtn = findViewById(R.id.logoutBtn);

        //make logout
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect back to login page
                logout();
                intent = new Intent(context,LoginActivity.class);
                //remove all previous stack activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

}