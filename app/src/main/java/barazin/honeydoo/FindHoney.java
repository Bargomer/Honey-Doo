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
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseObject;

import java.util.*;


public class FindHoney extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_honey);

        final EditText searchText = (EditText) findViewById(R.id.findHoneyText);

        //find button searches the parse database
        Button findBtn = (Button) findViewById(R.id.findBtn);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = searchText.getText().toString();



                //
                ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
                //query.whereEqualTo("username", "barazin");
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("score", "Error: " + e.getMessage());
                        } else {
                            final ProgressDialog dialog = new ProgressDialog(FindHoney.this);
                            dialog.setMessage("Found your honey!");
                            dialog.show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_honey, menu);
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

    //search the database for the honey
    void find(){

    }
}
