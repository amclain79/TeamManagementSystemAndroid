package Member;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import TMS.Informer;
import TMS.Main;
import edu.uta.cse5324.team1.teammanagementsystemandroid.R;
import entity.MemberTask;

public class ViewMemberTaskGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view_member_task_gui_layout);

        LinearLayout ll = findViewById(R.id.viewMemberTaskGUILayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        try{
            MemberTask memberTask = Main.viewMemberTaskController.viewMemberTask(Main.email);
            TextView memberTaskTV = new TextView(this);
            memberTaskTV.setText(memberTask.toString());
            memberTaskTV.setTextSize(24.0f);
            ll.addView(memberTaskTV, lp);
        } catch (RuntimeException e) {
            Informer.inform("Error Getting Task", findViewById(R.id.viewMemberTaskGUILayout));
        }
    }
}
