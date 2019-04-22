package User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Team;

public class OpenTeamsDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_open_teams_displayer_layout);

        List<Team> openTeams = Main.joinTeamController.getOpenTeams();
        Collections.sort(openTeams);

        LinearLayout cl = findViewById(R.id.open_teams_displayer_layout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final Intent next = new Intent(this, TeamMembersDisplayer.class);
        for(int i = 0; i < openTeams.size(); i++) {
            Button team = new Button(this);
            team.setId(i);
            team.setText(openTeams.get(i).teamName);
            team.setTextSize(24.0f);
            cl.addView(team, lp);
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
