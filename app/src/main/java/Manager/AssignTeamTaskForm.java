package Manager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import TMS.Informer;
import TMS.Main;
import controller.AssignTeamTaskController;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Team;
import model.TeamTaskRequest;

public class AssignTeamTaskForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_assign_team_task_form_layout);

        List<Team> teamsWithLeads = Main.assignTeamTaskController.getTeamsWithLeads();
        Collections.sort(teamsWithLeads);

        int chosenTeamIndex = getIntent().getExtras().getInt("chosenTeamIndex");
        final Team chosenTeam = teamsWithLeads.get(chosenTeamIndex);

        TextView titleTV = (TextView)findViewById(R.id.createTeamFeedbackGUITitle);
        titleTV.setText(String.format("Assign %s Task", chosenTeam.teamName));
    }

    public void submit(View view){
        List<Team> teamsWithLeads = Main.assignTeamTaskController.getTeamsWithLeads();
        Collections.sort(teamsWithLeads);

        int chosenTeamIndex = getIntent().getExtras().getInt("chosenTeamIndex");
        final Team chosenTeam = teamsWithLeads.get(chosenTeamIndex);

        TextView descriptionTV = findViewById(R.id.assignTeamTaskDescription);
        String description = descriptionTV.getText().toString();
        TextView durationTV = findViewById(R.id.createTeamFeedbackGUIFeedback);



        try {
            int duration = Integer.parseInt(durationTV.getText().toString());
            TeamTaskRequest ttr = new TeamTaskRequest(
                    description, chosenTeam.teamName, LocalDate.now().plusDays(duration)
            );
            Main.assignTeamTaskController.assignTeamTask(ttr);

            Intent managerMenuGUI = new Intent(this, ManagerMenuGUI.class);
            managerMenuGUI.putExtra("previous", "AssignTeamTask");
            startActivity(managerMenuGUI);
        } catch (NumberFormatException e) {
            Informer.inform("Invalid Duration", findViewById(R.id.assignTeamTaskFormLayout));
        } catch (AssignTeamTaskController.InvalidDescription e){
            Informer.inform("Invalid Description", findViewById(R.id.assignTeamTaskFormLayout));
        } catch (AssignTeamTaskController.InvalidDueDate e){
            Informer.inform("Invalid Duration", findViewById(R.id.assignTeamTaskFormLayout));
        }
    }
}
