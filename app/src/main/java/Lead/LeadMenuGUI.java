package Lead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ConcurrentHashMap;

import Person.LoginForm;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.MemberFeedback;
import entity.Profile;
import entity.TeamTask;

public class LeadMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("CreateTeamFeedbackGUI")){
            Informer.inform("Team Feedback Created", findViewById(R.id.leadMenuGUILayout));
        }
        if(previous.equals("AssignMemberTaskForm")){
            Informer.inform("Member Task Assigned", findViewById(R.id.leadMenuGUILayout));
        }
    }

    public void viewTeamTask(View view){
        TeamTask teamTask = Main.viewTeamTaskController.viewTeamTask(Main.email);
        if(teamTask == null){
            Informer.inform("No Task Assigned", findViewById(R.id.leadMenuGUILayout));
        } else {
            Intent viewTeamTask = new Intent(this, ViewTeamTaskGUI.class);
            startActivity(viewTeamTask);
        }
    }

    public void createTeamFeedback(View view){
        Intent createTeamFeedback = new Intent(this, CreateTeamFeedbackGUI.class);
        startActivity(createTeamFeedback);
    }

    public void assignMemberTask(View view){
        Intent assignMemberTask = new Intent(this, AssignMemberTaskGUI.class);
        startActivity(assignMemberTask);
    }

    public void viewMemberFeedback(View view){
        ConcurrentHashMap<String, MemberFeedback> feedbacks = Main.viewMemberFeedbackController.viewMemberFeedback(Main.email);
        if(feedbacks.size() > 0 ) {
            Intent viewMemberFeedback = new Intent(this, ViewMemberFeedbackGUI.class);
            startActivity(viewMemberFeedback);
        } else {
            Informer.inform("No Feedbacks", findViewById(R.id.leadMenuGUILayout));
        }
    }

    public void logout(View view){
        Intent loginForm = new Intent(this, LoginForm.class);
        loginForm.putExtra("previous", "LeadMenuGUI");
        startActivity(loginForm);
    }
}
