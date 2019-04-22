package User;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import entity.Team;

public class ProfileDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_displayer_layout);

        List<Team> openTeams = Main.joinTeamController.getOpenTeams();
        Collections.sort(openTeams);

        int chosenTeamIndex = getIntent().getExtras().getInt("chosenTeamIndex");
        Team chosenTeam = openTeams.get(chosenTeamIndex);

        List<Profile> profiles = Main.joinTeamController.getProfiles(chosenTeam);
        Collections.sort(profiles);

        int chosenMemberIndex = getIntent().getExtras().getInt("chosenMemberIndex");
        Profile profile = profiles.get(chosenMemberIndex);

        TextView titleTV = (TextView)findViewById(R.id.profileDisplayerTitle);
        titleTV.setText(String.format("%s Profile", profile.name));

        LinearLayout cl = findViewById(R.id.profile_displayer_layout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView profileTV = new TextView(this);
        profileTV.setText(profile.toString());
        profileTV.setTextSize(24.0f);
        cl.addView(profileTV, lp);
    }
}
