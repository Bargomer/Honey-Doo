package barazin.honeydoo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;


import java.util.List;


public class TaskListAcitivty extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Button submit = (Button) findViewById(R.id.taskSubmitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText taskTitle = (EditText) findViewById(R.id.taskTitleEdit);
                EditText honeyPointText = (EditText) findViewById(R.id.honeyPointsEdit);
                EditText dooPointText = (EditText) findViewById(R.id.dooPointsEdit);
                EditText descriptionText = (EditText) findViewById(R.id.taskDescription);
                int honeyPoints = Integer.parseInt(honeyPointText.getText().toString());
                int dooPoints = Integer.parseInt(dooPointText.getText().toString());

                ParseQuery<ParseObject> query = ParseQuery.getQuery("HoneyList");
                query.whereEqualTo("listId", getIntent().getStringExtra("id"));
                query.whereEqualTo("taskId", null);
                query.whereNotEqualTo("sucka", "temp");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> invites, ParseException e) {
                        if (e == null) {
                            // iterate over all messages and delete them
                            for (ParseObject invite : invites) {
                                invite.deleteInBackground();
                            }
                        } else {
                            //Handle condition here
                        }
                    }
                });

                ParseObject temp = ParseObject.create("HoneyList");
                temp.put("listId",getIntent().getStringExtra("id"));
                temp.put("taskId", taskTitle.getText().toString());
                temp.put("honeyPoints", honeyPoints);
                temp.put("dooPoints", dooPoints);
                temp.put("description", descriptionText.getText().toString());
                temp.put("honey", ParseUser.getCurrentUser().getUsername().toString());
                temp.saveInBackground();


                //TextView despText = (TextView) findViewById(R.id.despText);
                //return the list name back to other activity
                //
                Intent intent = new Intent(TaskListAcitivty.this, Task.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list_acitivty, menu);
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
