package ahhhlvin.c4q.nyc.retrofitstackoverflow;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alvin2 on 12/5/16.
 */

class Question {
    private String title;
    private String link;
    private String creation_date;

    @Override
    public String toString() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {

        long time = Long.parseLong(creation_date);
        Date date = new Date(time);

        return "Posted on: " + new SimpleDateFormat("EEE, MM/dd/yyyy hh:mm a", Locale.US).format(date);
    }
}
