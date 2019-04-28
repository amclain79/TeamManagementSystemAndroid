package Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.Collections;
import java.util.List;

import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import entity.Team;

public class NominateTeamLeadGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_nominate_team_lead_gui_layout);

        Team candidateTeam = Main.nominateLeadController.getTeam(Main.email);

        List<Profile> candidates = Main.nominateLeadController.getCandidateProfiles(Main.email);
        Collections.sort(candidates);

        LinearLayout ll = findViewById(R.id.nominateTeamLeadGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        final Intent next = new Intent(this, NominateTeamLeadCandidateGUI.class);
        for(int i = 0; i < candidates.size(); i++){
            Button candidate = new Button(this);
            candidate.setId(i);
            candidate.setText(candidates.get(i).name);
            candidate.setTextSize(24.0f);
            ll.addView(candidate, lp);
            candidate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        next.putExtra("candidateIndex", v.getId());
                        startActivity(next);
                    }
                }
            );
        }
    }
}
