package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import controller.CreateProjectController;
import gateway.ProjectStateManager;
import interactor.UserInteractor;
import model.CreateProjectRequest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateProjectForm extends AppCompatActivity {

    public static UserInteractor userInteractor = new UserInteractor(ProjectStateManager.getInstance());
    public static CreateProjectController createProjectController = new CreateProjectController(userInteractor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project_form_layout);
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
            createProjectController.createProject(cpr);
            TestDataLoader.loadTestData();

            Intent login = new Intent(this, LoginForm.class);
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
