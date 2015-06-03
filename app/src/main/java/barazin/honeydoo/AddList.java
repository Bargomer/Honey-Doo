package barazin.honeydoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddList extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        Button submit = (Button) findViewById(R.id.submitListName);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddList.this, ListActivity.class);
                EditText listName = (EditText) findViewById(R.id.listNameEdit);
                ParseObject temp = ParseObject.create("HoneyList");
                temp.put("listId", listName.getText().toString());
                temp.put("honey", ParseUser.getCurrentUser().getUsername().toString());
                temp.put("sucka", "temp");
                temp.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            //myObjectSavedSuccessfully();
                            //System.out.println("EXCELENT");

                        } else {
                            //myObjectSaveDidNotSucceed();
                            Log.i("ERRRRORR: ", e.getMessage());
                        }
                    }
                });

                startActivity(intent);
                //return the list name back to other activity
                //
                //startActivity(new Intent(AddList.this, ListActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_list, menu);
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
