package Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import model.NominationRequest;

public class NominateTeamLeadCandidateGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_nominate_team_lead_candidate_gui_layout);

        List<Profile> candidates = Main.nominateLeadController.getCandidateProfiles(Main.email);
        Collections.sort(candidates);

        int candidateIndex = getIntent().getExtras().getInt("candidateIndex");
        final Profile candidate = candidates.get(candidateIndex);

        LinearLayout ll = findViewById(R.id.nominateTeamLeadCandidateGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView candidateTV = new TextView(this);
        candidateTV.setText(candidate.toString());
        candidateTV.setTextSize(24.0f);
        ll.addView(candidateTV, lp);

        Button nominate = new Button(this);
        nominate.setText("Nominate");
        nominate.setTextSize(24.0f);
        ll.addView(nominate, lp);
        nominate.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nominate(v);
                }
            }
        );
    }

    public void nominate(View view){
        String candidateTeamName = Main.nominateLeadController.getTeam(Main.email).teamName;
        List<Profile> candidates = Main.nominateLeadController.getCandidateProfiles(Main.email);
        Collections.sort(candidates);
        int candidateIndex = getIntent().getExtras().getInt("candidateIndex");
        String candidateEmail = candidates.get(candidateIndex).email;
        Main.nominateLeadController.nominateLead(
                new NominationRequest(
                        candidateEmail, candidateTeamName, Main.email
                )
        );
        Intent next = new Intent(this, MemberMenuGUI.class);
        next.putExtra("previous", "NominateTeamLeadCandidateGUI");
        startActivity(next);
    }
}
