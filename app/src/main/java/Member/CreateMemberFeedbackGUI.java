package Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import model.MemberFeedbackRequest;

public class CreateMemberFeedbackGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_create_member_feedback_gui_layout);
    }

    public void submit(View v){
        TextView memberFeedbackTV = findViewById(R.id.createMemberFeedbackGUIFeedback);
        String memberFeedback = memberFeedbackTV.getText().toString();
        Main.createMemberFeedbackController.createMemberFeedback(
            new MemberFeedbackRequest(
                    Main.email, memberFeedback
            )
        );
        Intent next = new Intent(this, MemberMenuGUI.class);
        next.putExtra("previous", "CreateMemberFeedbackGUI");
        startActivity(next);
    }
}
