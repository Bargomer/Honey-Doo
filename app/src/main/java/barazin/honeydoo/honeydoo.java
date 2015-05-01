package barazin.honeydoo;

import android.app.Application;

import com.parse.Parse;

public class honeydoo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ekPAl3rcWOeXFSHBXAsbABVYSxDDiiEkUi6CFjjb", "FUrihUSMjrp1AdLyf3NdGP10xVimsVpxVFjB6NBc");

        //THESE ARE THE FIRST LINES OF CODE TO RUN WHEN YOU START YOUR APP

        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "ekPAl3rcWOeXFSHBXAsbABVYSxDDiiEkUi6CFjjb", "FUrihUSMjrp1AdLyf3NdGP10xVimsVpxVFjB6NBc");


        //delete lines after this, for test purposes only
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();
    }


}