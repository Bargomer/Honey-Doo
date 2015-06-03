package barazin.honeydoo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class RewardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        Button submit = (Button) findViewById(R.id.submitRewardName);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(RewardActivity.this, RewardListActivity.class);
                EditText rewardTitle = (EditText) findViewById(R.id.rewardNameEdit);
                EditText rewardDescription = (EditText) findViewById(R.id.rewardDescriptionEdit);
                ParseObject temp = ParseObject.create("HoneyList");
                temp.put("honey", ParseUser.getCurrentUser().getUsername().toString());
                temp.put("rewards", rewardTitle.getText().toString());
                temp.put("rewardDescription", rewardDescription.getText().toString());
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
        getMenuInflater().inflate(R.menu.menu_reward, menu);
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
