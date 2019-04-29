package Member;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;

public class ViewProfileGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_profile_gui_layout);

        Profile profile = Main.viewProfileController.viewProfile(Main.email);
        LinearLayout ll = findViewById(R.id.viewProfileGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView teamFeedbackTV = new TextView(this);
        teamFeedbackTV.setText(profile.toString());
        teamFeedbackTV.setTextSize(24.0f);
        ll.addView(teamFeedbackTV, lp);
    }
}
