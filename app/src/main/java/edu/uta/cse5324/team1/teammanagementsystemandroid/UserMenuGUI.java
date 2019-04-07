package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("createProfileForm"))
            Informer.inform("Profile Created", findViewById(R.id.userMenuView));
    }

    public void logout(View view){

    }

    public void joinTeam(View view){
        Intent joinTeam = new Intent(this, JoinTeamList.class);
        startActivity(joinTeam);
    }

    public void createTeam(View view){
        Intent createTeam = new Intent(this, CreateTeamForm.class);
        startActivity(createTeam);
    }
}