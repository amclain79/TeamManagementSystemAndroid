package edu.uta.cse5324.team1.teammanagementsystemandroid;

import androidx.appcompat.app.AppCompatActivity;
import controller.LoginController;
import gateway.ProjectStateManager;
import interactor.PersonInteractor;
import model.ProjectTypes.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginForm extends AppCompatActivity {

    public static String email;
    public static PersonInteractor personInteractor =
            new PersonInteractor(ProjectStateManager.getInstance());
    public static LoginController loginController = new LoginController(personInteractor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("createProjectForm"))
            Informer.inform("Project Created", findViewById(R.id.loginLayout));
    }

    public void login(View view){
        TextView emailTV = findViewById(R.id.email);
        email = emailTV.getText().toString();

        try {
            Role role = loginController.login(email);

            Intent next = null;
            switch(role){
                case PERSON:
                    next = new Intent(this, CreateProfileForm.class);
                    break;
                case USER:
                    next = new Intent(this, UserMenuGUI.class);
                    break;
                case MEMBER:
                    next = new Intent(this, MemberMenuGUI.class);
                    break;
                case LEAD:
                    next = new Intent(this, LeadMenuGUI.class);
                    break;
                case MANAGER:
                    next = new Intent(this, ManagerMenuGUI.class);
            }
            startActivity(next);
        } catch (LoginController.InvalidEmail e){
            Informer.inform("Invalid Email", findViewById(R.id.loginLayout));
        }
    }
}
