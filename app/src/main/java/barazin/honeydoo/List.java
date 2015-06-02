package barazin.honeydoo;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Jose on 6/23/15.
 */
@ParseClassName("List")

public class List extends ParseObject {

    private String listName;

    public String getListName() {

        return getString("listId");
    }

    public void setListName(String listName) {
        put("listId", listName);
    }
}
