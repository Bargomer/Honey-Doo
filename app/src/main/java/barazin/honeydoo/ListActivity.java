package barazin.honeydoo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    private static ArrayList<List> lists = new ArrayList<List>(1);
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        ListView listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListAdapter(
                this, lists);

        listView.setAdapter(listAdapter);

        //add list to the listview
        Button add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lists.add(new List("List Name"));
                listAdapter.notifyDataSetChanged();
            }
        });

        //remove list from the listview
        Button remove = (Button) findViewById(R.id.deleteButton);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lists.size() >= 1) {
                    lists.remove(lists.size() - 1);
                    listAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
