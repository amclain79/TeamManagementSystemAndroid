package Lead;

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

public class ViewMemberFeedbackGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_view_member_feedback_gui_layout);

        ConcurrentHashMap<String, Profile> profiles = Main.viewMemberFeedbackController.getMemberProfiles(Main.email);
        List<String> memberEmails = Collections.list(profiles.keys());
        Collections.sort(memberEmails);

        LinearLayout ll = findViewById(R.id.viewMemberFeedbackGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        Intent next = new Intent(this, ViewMemberFeedbackGUIFeedback.class);
        for(int i = 0; i < memberEmails.size(); i++){
            Button team = new Button(this);
            team.setId(i);
            team.setText(profiles.get(memberEmails.get(i)).name);
            team.setTextSize(24.0f);
            ll.addView(team, lp);
            team.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        next.putExtra("chosenMemberIndex", v.getId());
                        startActivity(next);
                    }
                }
            );
        }
    }
}
