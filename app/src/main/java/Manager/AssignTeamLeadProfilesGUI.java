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

public class AssignTeamLeadProfilesGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_assign_team_lead_profiles_gui_layout);

        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = Main.assignTeamLeadController.getNomineeProfilesByTeam();
        List<String> teamNames = Collections.list(nomineeProfilesByTeam.keys());
        Collections.sort(teamNames);

        int teamNameIndex = getIntent().getExtras().getInt("teamNameIndex");
        List<Profile> nomineeProfiles = nomineeProfilesByTeam.get(teamNames.get(teamNameIndex));

        LinearLayout ll = findViewById(R.id.assignTeamLeadProfilesGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView titleTV = findViewById(R.id.assignTeamLeadProfilesGUITitle);
        titleTV.setText(teamNames.get(teamNameIndex) + " Nominees");

        final Intent next = new Intent(this, AssignTeamLeadProfileGUI.class);
        for(int i = 0; i < nomineeProfiles.size(); i++){
            Button nomineeProfile = new Button(this);
            nomineeProfile.setId(i);
            nomineeProfile.setText(nomineeProfiles.get(i).name);
            nomineeProfile.setTextSize(24.0f);
            ll.addView(nomineeProfile, lp);
            nomineeProfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        next.putExtra("teamNameIndex", teamNameIndex);
                        next.putExtra("nomineeProfileIndex", v.getId());
                        startActivity(next);
                    }
                }
            );
        }
    }
}
