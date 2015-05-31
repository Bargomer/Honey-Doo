package barazin.honeydoo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class honeydoo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ekPAl3rcWOeXFSHBXAsbABVYSxDDiiEkUi6CFjjb", "FUrihUSMjrp1AdLyf3NdGP10xVimsVpxVFjB6NBc");

        ParseObject groupMessage = new ParseObject("Honey List");
        ParseACL groupACL = new ParseACL();


    }

}