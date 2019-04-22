package User;

import androidx.appcompat.app.AppCompatActivity;
import controller.CreateProfileController;
import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import model.CreateProfileRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateProfileForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_create_profile_form_layout);
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
                name, Main.email, edu, exp
        );

        try{
            Main.createProfileController.createProfile(cpr);
            Intent userMenu = new Intent(this, UserMenuGUI.class);
            userMenu.putExtra("previous", "CreateProfileForm");
            startActivity(userMenu);
        }catch (CreateProfileController.InvalidCreateProfileRequest e){
            Informer.inform("Invalid Create Profile Request", findViewById(R.id.createProfileLayout));
        }
    }
}
