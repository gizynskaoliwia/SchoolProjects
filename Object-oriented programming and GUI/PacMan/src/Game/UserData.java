package Game;

import java.io.Serializable;

public class UserData implements Serializable {
    public String points;

    public UserData(){

    }

    public UserData(String points) {
        this.points = points;
    }
}
