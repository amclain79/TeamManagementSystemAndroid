package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import controller.CreateProfileController;
import gateway.ProjectStateManager;
import interactor.UserInteractor;
import model.CreateProfileRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateProfileForm extends AppCompatActivity {

    public static UserInteractor userInteractor = new UserInteractor(ProjectStateManager.getInstance());
    public static CreateProfileController createProfileController = new CreateProfileController(userInteractor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_form_layout);
        Informer.inform("Profile not found.", findViewById(R.id.createProfileLayout));
    }

    public void submit(View view){
        TextView nameTV = findViewById(R.id.name);
        String name = nameTV.getText().toString();
        TextView eduTV = findViewById(R.id.education);
        String edu = eduTV.getText().toString();
        TextView expTV = findViewById(R.id.experience);
        String exp = expTV.getText().toString();

        CreateProfileRequest cpr = new CreateProfileRequest(
                name, LoginForm.email, edu, exp
        );

        try{
            createProfileController.createProfile(cpr);
            Intent userMenu = new Intent(this, UserMenuGUI.class);
            userMenu.putExtra("previous", "createProfileForm");
            startActivity(userMenu);
        }catch (CreateProfileController.InvalidCreateProfileRequest e){
            Informer.inform(e.getMessage(), findViewById(R.id.createProfileLayout));
        }
    }
}
