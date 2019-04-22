package User;

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
import Member.MemberMenuGUI;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import entity.Team;
import model.JoinTeamRequest;

public class TeamMembersDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_team_members_displayer_layout);

        List<Team> openTeams = Main.joinTeamController.getOpenTeams();
        Collections.sort(openTeams);

        int chosenTeamIndex = getIntent().getExtras().getInt("chosenTeamIndex");
        final Team chosenTeam = openTeams.get(chosenTeamIndex);

        TextView titleTV = (TextView)findViewById(R.id.teamMembersDisplayerTitle);
        titleTV.setText(String.format("%s Members", chosenTeam.teamName));

        List<Profile> profiles = Main.joinTeamController.getProfiles(chosenTeam);
        Collections.sort(profiles);

        LinearLayout linearLayout = findViewById(R.id.team_members_displayer_layout);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final Intent profileDisplayer = new Intent(this, ProfileDisplayer.class);
        profileDisplayer.putExtra("chosenTeamIndex", chosenTeamIndex);
        for(int i = 0; i < profiles.size(); i++) {
            Button memberBtn = new Button(this);
            memberBtn.setId(i);
            memberBtn.setText(profiles.get(i).name);
            memberBtn.setTextSize(24.0f);
            linearLayout.addView(memberBtn, layoutParams);
            memberBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profileDisplayer.putExtra("chosenMemberIndex", v.getId());
                        startActivity(profileDisplayer);
                    }
                }
            );
        }

        final Intent memberMenuGUI = new Intent(this, MemberMenuGUI.class);
        memberMenuGUI.putExtra("previous", "ProfileDisplayer");

        Button joinTeamBtn = new Button(this);
        joinTeamBtn.setText("Join Team");
        joinTeamBtn.setTextSize(24.0f);
        linearLayout.addView(joinTeamBtn, layoutParams);
        joinTeamBtn.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Main.joinTeamController.joinTeam(new JoinTeamRequest(chosenTeam, Main.email));
                    startActivity(memberMenuGUI);
                }
            }
        );
    }
}
