package poorguy.intelask;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by nguyentuananh on 13/6/15.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "Zrp2YNZiG7T3HHzXwgQCpWQsuTPZY9VDld1720Od", "OJ2vq6RNatSwPPN0jeSZ2Ln9biO4yG6lv3h1eDSs");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
