package Lead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.TeamTask;

public class ViewTeamTaskGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_view_team_task_gui_layout);

        LinearLayout ll = findViewById(R.id.viewTeamTaskGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        try{
            TeamTask teamTask = Main.viewTeamTaskController.viewTeamTask(Main.email);
            if(teamTask == null){
                Informer.inform("No Task Assigned", findViewById(R.id.viewTeamTaskGUILayout));
            } else {
                TextView teamTaskTV = new TextView(this);
                teamTaskTV.setText(teamTask.toString());
                teamTaskTV.setTextSize(24.0f);
                ll.addView(teamTaskTV, lp);
            }
        } catch (RuntimeException e) {
            Informer.inform("Error Getting Task", findViewById(R.id.viewTeamTaskGUILayout));
        }
    }
}
