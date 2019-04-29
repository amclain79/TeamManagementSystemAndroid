package Manager;

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
import java.util.concurrent.ConcurrentHashMap;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import model.AssignTeamLeadRequest;

public class AssignTeamLeadProfileGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_assign_team_lead_profile_gui_layout);

        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = Main.assignTeamLeadController.getNomineeProfilesByTeam();
        List<String> teamNames = Collections.list(nomineeProfilesByTeam.keys());
        Collections.sort(teamNames);

        int teamNameIndex = getIntent().getExtras().getInt("teamNameIndex");
        List<Profile> nomineeProfiles = nomineeProfilesByTeam.get(teamNames.get(teamNameIndex));

        int nomineeProfileIndex = getIntent().getExtras().getInt("nomineeProfileIndex");
        Profile nomineeProfile = nomineeProfiles.get(nomineeProfileIndex);

        LinearLayout ll = findViewById(R.id.assignTeamLeadProfileGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView nomineeProfileTV = new TextView(this);
        nomineeProfileTV.setText(nomineeProfile.toString());
        nomineeProfileTV.setTextSize(24.0f);
        ll.addView(nomineeProfileTV, lp);

        Button assign = new Button(this);
        assign.setText("Assign Team Lead");
        assign.setTextSize(24.0f);
        ll.addView(assign, lp);
        assign.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assignTeamLead(v);
                }
            }
        );
    }

    public void assignTeamLead(View v) {
        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = Main.assignTeamLeadController.getNomineeProfilesByTeam();
        List<String> teamNames = Collections.list(nomineeProfilesByTeam.keys());
        Collections.sort(teamNames);

        int teamNameIndex = getIntent().getExtras().getInt("teamNameIndex");
        List<Profile> nomineeProfiles = nomineeProfilesByTeam.get(teamNames.get(teamNameIndex));
        String teamName = teamNames.get(teamNameIndex);

        int nomineeProfileIndex = getIntent().getExtras().getInt("nomineeProfileIndex");
        Profile nomineeProfile = nomineeProfiles.get(nomineeProfileIndex);

        Main.assignTeamLeadController.assignTeamLead(
                new AssignTeamLeadRequest(
                        nomineeProfile, teamName
                )
        );

        Intent next = new Intent(this, ManagerMenuGUI.class);
        next.putExtra("previous", "AssignTeamLeadProfileGUI");
        startActivity(next);
    }
}
