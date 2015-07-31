package barazin.honeydoo;

import android.app.AlertDialog;
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
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.*;
import java.util.List;


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
                final String keyword = searchText.getText().toString();
                final ProgressDialog dialog = new ProgressDialog(FindHoney.this);
                dialog.setMessage("Finding your honey!");

                //Alert box definition
                final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(FindHoney.this);
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);

                ParseQuery<ParseObject> queryUser = ParseQuery.getQuery("_User"); //Check the User Table
                queryUser.whereEqualTo("username", keyword); //query the username column
                dialog.show();

                //find if honey exist in background
                queryUser.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> object, ParseException e) {

                        //If the user inputs a user that doesn't exist, then have an alert dialog box
                        //pop up to let them know they input an invalid user.
                        if (object.size() == 0) {

                            dlgAlert.setMessage("Your honey was not found!");
                            dlgAlert.setTitle("Sowwy");
                            dlgAlert.create().show();
                        }

                        //The user exist. Execute further commands.
                        else if (e == null) {

                            //Check to see if user already has a honey
                            ParseQuery<ParseObject> queryCouple = ParseQuery.getQuery("Couple"); //Check the Couple table
                            queryCouple.whereEqualTo("honey2", keyword);
                            queryCouple.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    //If user (keyword) has a honey, then inform the person that the person is taken

                                    if (list.size() > 0) {
                                        dlgAlert.setMessage(keyword + " is already taken!");
                                        dlgAlert.setTitle("Sowwy");
                                        dlgAlert.create().show();
                                    }//end if

                                    //The user doesn't have a honey. Now to check if the current user is a temp honey
                                    //If they are, then it's a mutual match. If not, store searched user in current users
                                    //Temp honey
                                    else {
                                        ParseQuery<ParseObject> queryTempHoney = ParseQuery.getQuery("Couple");
                                        queryTempHoney.whereEqualTo("honey1", keyword); //check potential honey's column
                                        queryTempHoney.whereEqualTo("tempHoney", ParseUser.getCurrentUser().getUsername().toString()); //check to see if current user is already wanted as a mate
                                        queryTempHoney.findInBackground(new FindCallback<ParseObject>() {
                                            @Override
                                            public void done(List<ParseObject> list, ParseException e) {

                                                //It's a mutal match! Isn't life wonderful?
                                                if (list.size() > 0) {
                                                    ///////////////////
                                                    ////////////
                                                    dlgAlert.setMessage("It's a mutual match!");
                                                    dlgAlert.setTitle("YAY!");
                                                    dlgAlert.create().show();

                                                    ParseQuery<ParseObject> insertFirstUser = ParseQuery.getQuery("Couple");
                                                    insertFirstUser.whereEqualTo("honey1", ParseUser.getCurrentUser().getUsername().toString());

                                                    // Retrieve the object by id
                                                    insertFirstUser.getFirstInBackground(new GetCallback<ParseObject>() {
                                                        public void done(ParseObject firstHoney, ParseException e) {
                                                            if (e == null) {

                                                                firstHoney.put("honey1",ParseUser.getCurrentUser().getUsername().toString() );
                                                                firstHoney.put("honey2", keyword);
                                                                firstHoney.saveInBackground();
                                                            }
                                                        }
                                                    });//end of getinBackground for first user

                                                    ParseQuery<ParseObject> insertSecondUser = ParseQuery.getQuery("Couple");
                                                    insertSecondUser.whereEqualTo("honey1", keyword);

                                                    // Retrieve the object by id
                                                    insertSecondUser.getFirstInBackground(new GetCallback<ParseObject>() {
                                                        public void done(ParseObject secondHoney, ParseException e) {
                                                            if (e == null) {


                                                                secondHoney.put("honey1", keyword);
                                                                secondHoney.put("honey2", ParseUser.getCurrentUser().getUsername().toString());
                                                                secondHoney.saveInBackground();
                                                            }
                                                        }
                                                    });

                                                } //end of if, if there's a mutual match

                                                //There's no mutual match. Put searched user in tempHoney position
                                                else {
                                                    ParseObject temp = new ParseObject("Couple");
                                                    temp.put("honey1", ParseUser.getCurrentUser().getUsername().toString());
                                                    temp.put("tempHoney", keyword);
                                                    temp.saveInBackground();
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                        }
                        //get rid of the dialog box
                        dialog.dismiss();
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
}
