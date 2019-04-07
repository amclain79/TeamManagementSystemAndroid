package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateProfileForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_form_layout);
    }

    public void submit(View view){
        Intent userMenu = new Intent(this, UserMenuGUI.class);
        startActivity(userMenu);
    }
}
