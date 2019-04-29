package Lead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import Manager.ManagerMenuGUI;
import TMS.Informer;
import TMS.Main;
import controller.AssignTeamTaskController;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.Profile;
import entity.Team;
import model.AssignMemberTaskRequest;
import model.TeamTaskRequest;

public class AssignMemberTaskForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_assign_member_task_form_layout);

        ConcurrentHashMap<String, Profile> profiles = Main.assignMemberTaskController.getMemberProfiles(Main.email);
        List<String> memberEmails = Collections.list(profiles.keys());
        Collections.sort(memberEmails);

        int chosenMemberIndex = getIntent().getExtras().getInt("chosenMemberIndex");
        String chosenMemberEmail = memberEmails.get(chosenMemberIndex);

        TextView titleTV = (TextView)findViewById(R.id.assignMemberTaskFormTitle);
        titleTV.setText(String.format("Assign %s Task", profiles.get(memberEmails.get(chosenMemberIndex)).name));
    }

    public void submit(View view){
        ConcurrentHashMap<String, Profile> profiles = Main.assignMemberTaskController.getMemberProfiles(Main.email);
        List<String> memberEmails = Collections.list(profiles.keys());
        Collections.sort(memberEmails);

        int chosenMemberIndex = getIntent().getExtras().getInt("chosenMemberIndex");
        String chosenMemberEmail = memberEmails.get(chosenMemberIndex);

        TextView descriptionTV = findViewById(R.id.assignMemberTaskDescription);
        String description = descriptionTV.getText().toString();
        TextView durationTV = findViewById(R.id.assignMemberTaskDuration);

        try {
            int duration = Integer.parseInt(durationTV.getText().toString());
            AssignMemberTaskRequest mtr = new AssignMemberTaskRequest(
                    duration, chosenMemberEmail, description
            );
            Main.assignMemberTaskController.assignMemberTask(mtr);

            Intent leadMenuGUI = new Intent(this, LeadMenuGUI.class);
            leadMenuGUI.putExtra("previous", "AssignMemberTaskForm");
            startActivity(leadMenuGUI);
        } catch (NumberFormatException e) {
            Informer.inform("Invalid Duration", findViewById(R.id.assignMemberTaskFormLayout));
        } catch (AssignTeamTaskController.InvalidDescription e){
            Informer.inform("Invalid Description", findViewById(R.id.assignMemberTaskFormLayout));
        } catch (AssignTeamTaskController.InvalidDueDate e){
            Informer.inform("Invalid Duration", findViewById(R.id.assignMemberTaskFormLayout));
        }
    }
}
