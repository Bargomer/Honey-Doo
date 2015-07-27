package barazin.honeydoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;


public class Task extends ActionBarActivity {

    HoneyListAdapter listAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ParseObject.registerSubclass(List.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //add list to the listview
        Button add = (Button) findViewById(R.id.addTaskBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //go to new view to make a list
                Intent intent = new Intent(Task.this, TaskListAcitivty.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

//        //remove list from the listview
//        Button remove = (Button) findViewById(R.id.deleteButton);
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (lists.size() >= 1) {
//                    lists.remove(lists.size() - 1);
//                    listAdapter.notifyDataSetChanged();
//                }
//            }
//        });

        //take user to list
        listView = (ListView) findViewById(R.id.taskListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Task.this, TaskDescription.class);
                HoneyList project = listAdapter.getItem(i);
                intent.putExtra("taskDesc", project.getTaskId());
                startActivity(intent);
            }
        });

        syncList();
    }

    private void syncList() {
        ParseQuery<HoneyList> query = ParseQuery.getQuery(HoneyList.class);
        query.whereEqualTo("listId", getIntent().getStringExtra("id"));
        query.whereEqualTo("honey", ParseUser.getCurrentUser().getUsername().toString());
        query.whereNotEqualTo("taskId", null);
        final ProgressDialog dialog = new ProgressDialog(Task.this);
        dialog.setMessage("Retrieving Task!");
        dialog.show();
        query.findInBackground(new FindCallback<HoneyList>() {
            @Override
            public void done(java.util.List<HoneyList> list, ParseException e) {
                listAdapter = new HoneyListAdapter(Task.this, list);
                listView.setAdapter(listAdapter);
            }
        });
        dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
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
