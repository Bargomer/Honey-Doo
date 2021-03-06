package barazin.honeydoo;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Jose on 6/23/15.
 */
@ParseClassName("HoneyList")
public class HoneyList extends ParseObject {

    private String objectId;
    private int taskIcon = R.drawable.honeybee;
    private int honeyIcon = R.drawable.honey;
    private int dooIcon = R.drawable.doo;
    private String rewards;

    public String getRewardDescription() {
        return getString("rewardDescription");
    }

    public void setRewardDescription(String rewardDescription) {
        put("rewardDescription", rewardDescription);
    }

    public String getRewards() {
        return getString("rewards");
    }

    public void setRewards(String rewards) {
        put("rewards", rewards);
    }

    private String rewardDescription;



    public String getListId() {
        return getString("listId");
    }

    public void setListId(String listId) {
        put("listId", listId);
    }

    private String listId;

    public int getTaskIcon() {
        return taskIcon;
    }

    public int getHoneyIcon() {
        return honeyIcon;
    }

    public int getDooIcon() {
        return dooIcon;
    }

    @Override
    public void setObjectId(String objectId) {
        put("objectId", objectId);
    }

    public String getTaskId() {
        return getString("taskId");
    }

    public void setTaskId(String taskId) {
        put("taskId", taskId);
    }

    public String getDescription() {
        return getString("description");
    }

    public String getHoney() {
        return getString("honey");
    }

    public void setHoney(String honey) {
        put("honey", honey);
    }

    public String getSucka() {
        return getString("sucka");
    }

    public void setSucka(String sucka) {
        put("sucka", sucka);
    }

    public int getHoneyPoints() {
        return getInt("honeyPoints");
    }

    public void setHoneyPoints(int honeyPoints) {
        put("honeyPoints", honeyPoints);
    }

    public int getDooPoints() {
        return getInt("dooPoints");
    }

    public void setDooPoints(int dooPoints) {
        put("dooPoints", dooPoints);
    }

}