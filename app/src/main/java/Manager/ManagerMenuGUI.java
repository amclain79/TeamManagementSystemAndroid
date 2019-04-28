package Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import TMS.Informer;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;

public class ManagerMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("AssignTeamTask")) {
            Informer.inform("Team Task Assigned", findViewById(R.id.managerMenuView));
        }
    }

    public void assignTeamTask(View view){
        Intent assignTeamTaskForm = new Intent(this, AssignTeamTaskGUI.class);
        startActivity(assignTeamTaskForm);
    }

    public void assignTeamLead(View view){
        Intent assignTeamLeadForm = new Intent(this, AssignTeamLeadGUI.class);
        startActivity(assignTeamLeadForm);
    }

    public void viewTeamFeedback(View view){
        Intent viewTeamFeedbackForm = new Intent(this, ViewTeamFeedbackGUI.class);
        startActivity(viewTeamFeedbackForm);
    }
}
