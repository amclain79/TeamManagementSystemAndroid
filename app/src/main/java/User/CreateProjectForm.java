package User;

import androidx.appcompat.app.AppCompatActivity;

import Person.LoginForm;
import controller.CreateProjectController;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import TMS.TestDataLoader;
import model.CreateProjectRequest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateProjectForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_create_project_form_layout);
    }

    public void submit(View view){
        TextView maxTeamsTV = findViewById(R.id.maxTeams);
        int maxTeams = Integer.parseInt(maxTeamsTV.getText().toString());
        TextView maxMembersTV = findViewById(R.id.maxMembers);
        int maxMembers = Integer.parseInt(maxMembersTV.getText().toString());
        TextView minFeedbacksTV = findViewById(R.id.minFeedbacks);
        int minFeedbacks = Integer.parseInt(minFeedbacksTV.getText().toString());

        CreateProjectRequest cpr = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );

        try {
            Main.createProjectController.createProject(cpr);
            TestDataLoader.loadTestData();

            Intent login = new Intent(this, LoginForm.class);
            login.putExtra("previous", "CreateProjectForm");
            startActivity(login);
        } catch (CreateProjectController.InvalidNumberOfTeams e){
            Informer.inform("Invalid Number Of Teams", findViewById(R.id.createProjectLayout));
        } catch (CreateProjectController.InvalidNumberOfMembers e){
            Informer.inform("Invalid Number Of Members", findViewById(R.id.createProjectLayout));
        } catch (CreateProjectController.InvalidNumberOfFeedbacks e){
            Informer.inform("Invalid Number Of Feedbacks", findViewById(R.id.createProjectLayout));
        }
    }
}
