package barazin.honeydoo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;


import java.util.List;

public class TaskListAcitivty extends ActionBarActivity {

   /* //TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };*/

    EditText taskTitle;
    EditText honeyPointText;
    EditText dooPointText;
    EditText descriptionText;
    int honeyPoints;
    int dooPoints;
    Button submit;
    boolean validationError;
    StringBuilder validationErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        submit = (Button) findViewById(R.id.taskSubmitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                taskTitle = (EditText) findViewById(R.id.taskTitleEdit);
                honeyPointText = (EditText) findViewById(R.id.honeyPointsEdit);
                dooPointText = (EditText) findViewById(R.id.dooPointsEdit);
                descriptionText = (EditText) findViewById(R.id.taskDescription);
                honeyPoints = Integer.parseInt(honeyPointText.getText().toString());
                dooPoints = Integer.parseInt(dooPointText.getText().toString());

                //checkFieldsForEmptyValues();
                /*//set listeners
                taskTitle.addTextChangedListener(textWatcher);
                honeyPointText.addTextChangedListener(textWatcher);
                dooPointText.addTextChangedListener(textWatcher);
                descriptionText.addTextChangedListener(textWatcher);*/
                if (validationError) {
                    Toast.makeText(TaskListAcitivty.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

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

                checkFieldsForEmptyValues();
                if (validationError) {
                    Toast.makeText(TaskListAcitivty.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }
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

    private  void checkFieldsForEmptyValues(){

        validationErrorMessage = new StringBuilder("Please ");
        String s1 = Integer.toString(honeyPoints);;
        String s2 = Integer.toString(dooPoints);
        String s3 = descriptionText.toString();
        String s4 = taskTitle.toString();

        if(s4.trim().isEmpty()){
            validationError = true;
            validationErrorMessage.append("enter a task title");
        }

        if (s3.trim().isEmpty()) {
            if (validationError) {
                validationErrorMessage.append(", and ");
            }
            validationError = true;
            validationErrorMessage.append("title description");
        }
        if (s1.trim().isEmpty()) {
            if (validationError) {
                validationErrorMessage.append(", and ");
            }
            validationError = true;
            validationErrorMessage.append("honey points");
        }
        if (s2.trim().isEmpty()) {
            if (validationError) {
                validationErrorMessage.append(", and ");
            }
            validationError = true;
            validationErrorMessage.append("doo points");
        }
        validationErrorMessage.append(".");
        //validationError = true;
       /* String s1 = Integer.toString(honeyPoints);;
        String s2 = Integer.toString(dooPoints);
        String s3 = descriptionText.toString();
        String s4 = taskTitle.toString();

        if(!s1.trim().isEmpty() && !s2.trim().isEmpty() && !s3.trim().isEmpty() && !s4.trim().isEmpty());*/
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
