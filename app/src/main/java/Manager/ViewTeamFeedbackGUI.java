package Manager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.TeamFeedback;

public class ViewTeamFeedbackGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_view_team_feedback_gui_layout);

        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = Main.viewTeamFeedbacksController.viewTeamFeedbacks();
        List<String> teamNames = Collections.list(teamFeedbacks.keys());
        Collections.sort(teamNames);

        LinearLayout ll = findViewById(R.id.viewTeamFeedbackGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        final Intent next = new Intent(this, ViewTeamFeedbackGUIFeedback.class);
        for(int i = 0; i < teamNames.size(); i++){
            Button team = new Button(this);
            team.setId(i);
            team.setText(teamNames.get(i));
            team.setTextSize(24.0f);
            ll.addView(team, lp);
            team.setOnClickListener(
                new View.OnClickListener() {
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
