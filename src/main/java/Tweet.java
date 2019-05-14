import java.util.Date;

public class Tweet {
    String username;
    String text;
    Date date;

    public void setUsername(String user) {
        username = user;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}