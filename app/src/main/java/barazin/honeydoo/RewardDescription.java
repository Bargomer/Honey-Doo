package barazin.honeydoo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class RewardDescription extends ActionBarActivity {

    ListView listView;
    RewardDesAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ParseObject.registerSubclass(HoneyList.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_description);

        Button add = (Button) findViewById(R.id.purchaseReward);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(RewardDescription.this);
                builder1.setMessage("You do not have enough points for that!");
                builder1.setCancelable(true);
                builder1.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        ParseQuery<HoneyList> query = ParseQuery.getQuery(HoneyList.class);
        query.whereContains("rewards", getIntent().getStringExtra("rewardId"));
        query.findInBackground(new FindCallback<HoneyList>() {
            @Override
            public void done(List<HoneyList> list, ParseException e) {
//                    listAdapter = new RewardDesAdapter(RewardDescription.this, list);
//                    listView.setAdapter(listAdapter);
                HoneyList project = list.get(0);
                TextView temp = (TextView) findViewById(R.id.rewardDetDescriptionText);
                final ProgressDialog dialog = new ProgressDialog(RewardDescription.this);
                dialog.setMessage(project.getRewardDescription());
               // dialog.show();
                temp.setText(project.getRewardDescription());
            }
        });
        //temp.setText(getIntent().getStringExtra("rewardId"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reward_description, menu);
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
