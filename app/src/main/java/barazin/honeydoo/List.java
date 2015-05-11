package barazin.honeydoo;

/**
 * Created by Timadeus on 5/10/2015.
 */
public class List {

    private String listName;

    List(String listname) {
        this.listName = listname;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
