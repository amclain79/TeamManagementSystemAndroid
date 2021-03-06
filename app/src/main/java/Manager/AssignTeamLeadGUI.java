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
import entity.Profile;

public class AssignTeamLeadGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_assign_team_lead_gui_layout);

        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = Main.assignTeamLeadController.getNomineeProfilesByTeam();
        List<String> teamNames = Collections.list(nomineeProfilesByTeam.keys());
        Collections.sort(teamNames);

        LinearLayout ll = findViewById(R.id.assignTeamLeadGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        final Intent next = new Intent(this, AssignTeamLeadProfilesGUI.class);
        for(int i = 0; i < teamNames.size(); i++){
            Button teamName = new Button(this);
            teamName.setId(i);
            teamName.setText(teamNames.get(i));
            teamName.setTextSize(24.0f);
            ll.addView(teamName, lp);
            teamName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        next.putExtra("teamNameIndex", v.getId());
                        startActivity(next);
                    }
                }
            );
        }
    }
}
