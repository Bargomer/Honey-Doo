package barazin.honeydoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.*;
import java.util.List;


public class WelcomeActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "barazin.honeydoo.MESSAGE";
    private TextView textView;
    private Button tempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String currentUser = ParseUser.getCurrentUser().getUsername();
        setContentView(R.layout.activity_display_message);

        textView = (TextView)this.findViewById(R.id.insertName);
        textView.setText(currentUser + "!");

        tempButton = (Button)this.findViewById(R.id.findHoneyBtn);

        //if user has a honey, hide the find honey button
        ParseQuery<ParseObject> check = new ParseQuery("Couple");
        //check.whereEqualTo("honey1", ParseUser.getCurrentUser().getUsername().toString());
        check.whereMatches("honey2", ParseUser.getCurrentUser().getUsername().toString());
        final ProgressDialog dialog = new ProgressDialog(WelcomeActivity.this);


        check.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> object, com.parse.ParseException e) {

                if (object.size() == 0) {
                    tempButton.setVisibility(View.VISIBLE);
                } else if (e == null) {
                    tempButton.setVisibility(View.GONE);

                }
            }
        });
        //reward button
        Button rewardsBtn = (Button) findViewById(R.id.rewardsButton);
        rewardsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, RewardActivity.class));
            }
        });
        //logout
        Button actionButton = (Button) findViewById(R.id.logout);
        actionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                logout();
            }
        });

        tempButton = (Button)this.findViewById(R.id.findHoneyBtn);
        //find a honey
        Button honeyBtn = (Button) findViewById(R.id.findHoneyBtn);
        honeyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, FindHoney.class));
                //tempButton.setVisibility(View.GONE);
            }
        });
    }

    public void logout(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // This will now be null
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void sendList(View view) {

        Intent intent = new Intent(this, ListActivity.class);
        Button editText = (Button) findViewById(R.id.list);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
