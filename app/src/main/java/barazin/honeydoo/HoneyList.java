package barazin.honeydoo;

/**
 * Created by Jose on 5/10/2015.
 */
public class HoneyList {

    private Object user;
    private String description;
    private int points;
    private int photo;
    private static int totalHoneyPoints = 0; // Accumulates the total amount of points for the list

    // Constructor
    public HoneyList(String description, int points,int photo){
        this.description = description;
        this.points = points;
        this.photo = photo;
    }

    public Object getName(){
        return user;
    }

    public void setName(Object user){
        this.user = user;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String item){
        this.description = description;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getTotalHoneyPoints(){
        return totalHoneyPoints;
    }

    public void setTotalHoneyPoints(int totalHoneyPoints){
        this.totalHoneyPoints = totalHoneyPoints;
    }
}
