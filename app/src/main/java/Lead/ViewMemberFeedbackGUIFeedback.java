package Lead;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.MemberFeedback;
import entity.Profile;

public class ViewMemberFeedbackGUIFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_view_member_feedback_gui_feedback_layout);

        int chosenMemberIndex = getIntent().getExtras().getInt("chosenMemberIndex");
        ConcurrentHashMap<String, Profile> profiles = Main.viewMemberFeedbackController.getMemberProfiles(Main.email);
        List<String> memberEmails = Collections.list(profiles.keys());
        Collections.sort(memberEmails);
        Profile chosenProfile = profiles.get(memberEmails.get(chosenMemberIndex));

        ConcurrentHashMap<String, MemberFeedback> feedbacks = Main.viewMemberFeedbackController.viewMemberFeedback(Main.email);
        MemberFeedback feedback = feedbacks.get(chosenProfile.email);

        if(feedback == null){
            TextView titleTV = findViewById(R.id.viewMemberFeedbackGUIFeedbackTitle);
            titleTV.setText(String.format("No %s Feedback", chosenProfile.name));
        } else {
            TextView titleTV = findViewById(R.id.viewMemberFeedbackGUIFeedbackTitle);
            titleTV.setText(String.format("%s Feedback", chosenProfile.name));

            LinearLayout ll = findViewById(R.id.viewMemberFeedbackGUIFeedbackLayout);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

            TextView memberFeedbackTV = new TextView(this);
            memberFeedbackTV.setText(feedback.toString());
            memberFeedbackTV.setTextSize(24.0f);
            ll.addView(memberFeedbackTV, lp);
        }
    }
}
