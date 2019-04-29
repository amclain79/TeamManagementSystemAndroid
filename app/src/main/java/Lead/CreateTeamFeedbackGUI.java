package Lead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import model.CreateTeamFeedbackRequest;

public class CreateTeamFeedbackGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_create_team_feedback_gui_layout);
    }

    public void submit(View v){
        TextView teamFeedbackTV = findViewById(R.id.createTeamFeedbackGUIFeedback);
        String teamFeedback = teamFeedbackTV.getText().toString();
        Main.createTeamFeedbackController.createTeamFeedback(
            new CreateTeamFeedbackRequest(
                Main.email, teamFeedback
            )
        );
        Intent next = new Intent(this, LeadMenuGUI.class);
        next.putExtra("previous", "CreateTeamFeedbackGUI");
        startActivity(next);
    }
}
