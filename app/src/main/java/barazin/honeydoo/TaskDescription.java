package barazin.honeydoo;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class TaskDescription extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);

        ParseQuery<HoneyList> query = ParseQuery.getQuery(HoneyList.class);
        query.whereEqualTo("taskId", getIntent().getStringExtra("taskDesc"));
        query.whereEqualTo("honey", ParseUser.getCurrentUser().getUsername().toString());
        query.findInBackground(new FindCallback<HoneyList>() {
            @Override
            public void done(List<HoneyList> list, ParseException e) {

                HoneyList project = list.get(0);
                TextView temp = (TextView) findViewById(R.id.taskDescriptionText);
                temp.setText(project.getDescription());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
