package Manager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.Collections;
import java.util.List;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Team;

public class AssignTeamTaskGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_assign_team_task_gui_layout);

        List<Team> teamsWithLeads = Main.assignTeamTaskController.getTeamsWithLeads();
        Collections.sort(teamsWithLeads);

        LinearLayout ll = findViewById(R.id.assignTeamTaskGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final Intent next = new Intent(this, AssignTeamTaskForm.class);
        for(int i = 0; i < teamsWithLeads.size(); i++){
            Button team = new Button(this);
            team.setId(i);
            team.setText(teamsWithLeads.get(i).teamName);
            team.setTextSize(24.0f);
            ll.addView(team, lp);
            team.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        next.putExtra("chosenTeamIndex", v.getId());
                        startActivity(next);
                    }
                }
            );
        }
    }
}
