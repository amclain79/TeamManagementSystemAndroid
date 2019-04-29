package Manager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import Person.LoginForm;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import entity.TeamFeedback;

public class ManagerMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("AssignTeamTask")) {
            Informer.inform("Team Task Assigned", findViewById(R.id.managerMenuView));
        }
        if(previous.equals("AssignTeamLeadProfileGUI")){
            Informer.inform("Team Lead Assigned", findViewById(R.id.managerMenuView));
        }
    }

    public void assignTeamTask(View view){
        Intent assignTeamTaskForm = new Intent(this, AssignTeamTaskGUI.class);
        startActivity(assignTeamTaskForm);
    }

    public void assignTeamLead(View view){
        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = Main.assignTeamLeadController.getNomineeProfilesByTeam();
        if(nomineeProfilesByTeam.size() > 0) {
            Intent assignTeamLeadForm = new Intent(this, AssignTeamLeadGUI.class);
            startActivity(assignTeamLeadForm);
        }else{
            Informer.inform("No Nominations", findViewById(R.id.managerMenuView));
        }
    }

    public void viewTeamFeedback(View view){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = Main.viewTeamFeedbacksController.viewTeamFeedbacks();
        if(teamFeedbacks.size() > 0 ) {
            Intent viewTeamFeedbackForm = new Intent(this, ViewTeamFeedbackGUI.class);
            startActivity(viewTeamFeedbackForm);
        } else {
            Informer.inform("No Feedbacks", findViewById(R.id.managerMenuView));
        }
    }

    public void logout(View view){
        Intent loginForm = new Intent(this, LoginForm.class);
        loginForm.putExtra("previous", "ManagerMenuGUI");
        startActivity(loginForm);
    }
}
