package Manager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.TeamFeedback;

public class ViewTeamFeedbackGUIFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_view_team_feedback_gui_feedback_layout);

        int chosenTeamIndex = getIntent().getExtras().getInt("chosenTeamIndex");

        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = Main.viewTeamFeedbacksController.viewTeamFeedbacks();
        List<String> teamNames = Collections.list(teamFeedbacks.keys());
        Collections.sort(teamNames);

        TeamFeedback teamFeedback = teamFeedbacks.get(teamNames.get(chosenTeamIndex));

        LinearLayout ll = findViewById(R.id.viewTeamFeedbackGUIFeedbackLayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView teamFeedbackTV = new TextView(this);
        teamFeedbackTV.setText(teamFeedback.toString());
        teamFeedbackTV.setTextSize(24.0f);
        ll.addView(teamFeedbackTV, lp);
    }
}
