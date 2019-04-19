package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import controller.CreateTeamController;
import model.CreateTeamRequest;

public class CreateTeamForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_team_form_layout);
    }

    public void createTeam(View view){
        TextView teamNameTV = findViewById(R.id.teamName);
        String teamName = teamNameTV.getText().toString();

        CreateTeamRequest ctr = new CreateTeamRequest(
                teamName, Main.email
        );

        try{
            Main.createTeamController.createTeam(ctr);
            Intent memberMenu = new Intent(this, MemberMenuGUI.class);
            memberMenu.putExtra("previous", "CreateTeamForm");
            startActivity(memberMenu);
        }catch (CreateTeamController.InvalidTeamName e){
            Informer.inform("Invalid Team Name.", findViewById(R.id.createProfileLayout));
        }
    }
}
