package Member;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import Person.LoginForm;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.MemberTask;
import entity.Profile;

public class MemberMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("CreateTeamForm")) {
            Informer.inform("Team Created. Member Added.", findViewById(R.id.memberMenuGUILayout));
        }
        if(previous.equals("NominateTeamLeadCandidateGUI")) {
            Informer.inform("Member Nominated.", findViewById(R.id.memberMenuGUILayout));
        }
    }

    public void nominateTeamLead(View view){
        if(Main.nominateLeadController.getTeam(Main.email).hasLead()) {
            Informer.inform("Team Lead Assigned", findViewById(R.id.memberMenuGUILayout));
        } else {
            List<Profile> candidates = Main.nominateLeadController.getCandidateProfiles(Main.email);
            if(candidates.size() > 0) {
                Intent nominateTeamLead = new Intent(this, NominateTeamLeadGUI.class);
                startActivity(nominateTeamLead);
            } else {
                Informer.inform("No Candidates", findViewById(R.id.memberMenuGUILayout));
            }
        }
    }

    public void viewProfile(View view){
        Intent viewProfile = new Intent(this, ViewProfileGUI.class);
        startActivity(viewProfile);
    }

    public void viewMemberTask(View view){
        MemberTask memberTask = Main.viewMemberTaskController.viewMemberTask(Main.email);
        if(memberTask == null){
            Informer.inform("No Task Assigned", findViewById(R.id.memberMenuGUILayout));
        } else {
            Intent viewMemberTask = new Intent(this, ViewMemberTaskGUI.class);
            startActivity(viewMemberTask);
        }
    }

    public void createMemberFeedback(View view){
        Intent createMemberFeedback = new Intent(this, CreateMemberFeedbackGUI.class);
        startActivity(createMemberFeedback);
    }

    public void logout(View view){
        Intent loginForm = new Intent(this, LoginForm.class);
        loginForm.putExtra("previous", "MemberMenuGUI");
        startActivity(loginForm);
    }
}
