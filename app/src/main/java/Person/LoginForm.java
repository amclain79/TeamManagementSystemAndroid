package Person;

import androidx.appcompat.app.AppCompatActivity;
import controller.LoginController;
import User.CreateProfileForm;
import TMS.Informer;
import Lead.LeadMenuGUI;
import TMS.Main;
import Manager.ManagerMenuGUI;
import Member.MemberMenuGUI;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import User.UserMenuGUI;
import model.ProjectTypes.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_login_form_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("CreateProjectForm"))
            Informer.inform("Project Created", findViewById(R.id.loginLayout));
    }

    public void login(View view){
        TextView emailTV = findViewById(R.id.email);
        Main.email = emailTV.getText().toString();

        try {
            Role role = Main.loginController.login(Main.email);

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
            next.putExtra("previous", "LoginForm");
            startActivity(next);
        } catch (LoginController.InvalidEmail e){
            Informer.inform("Invalid Email", findViewById(R.id.loginLayout));
        }
    }
}
