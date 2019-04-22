package Member;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import TMS.Informer;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;

public class MemberMenuGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_menu_gui_layout);

        String previous = getIntent().getExtras().getString("previous");
        if(previous.equals("CreateTeamForm")) {
            Informer.inform("Team Created. Member Added.", findViewById(R.id.memberMenuView));
        }
    }
}
