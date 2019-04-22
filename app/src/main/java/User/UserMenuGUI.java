package User;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Person.LoginForm;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;

public class UserMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("CreateProfileForm"))
            Informer.inform("Profile Created", findViewById(R.id.userMenuView));
    }

    public void logout(View view){
        Intent loginForm = new Intent(this, LoginForm.class);
        loginForm.putExtra("previous", "UserMenuGUI");
        startActivity(loginForm);
    }

    public void joinTeam(View view){
        if(Main.queryController.areTeamsFull()){
            Informer.inform("All Teams Full.", findViewById(R.id.userMenuView));
        } else {
            Intent joinTeam = new Intent(this, OpenTeamsDisplayer.class);
            startActivity(joinTeam);
        }
    }

    public void createTeam(View view){
        if(Main.queryController.isMaxTeams()){
            Informer.inform("Max Teams Reached.", findViewById(R.id.userMenuView));
        } else {
            Intent createTeam = new Intent(this, CreateTeamForm.class);
            startActivity(createTeam);
        }
    }
}