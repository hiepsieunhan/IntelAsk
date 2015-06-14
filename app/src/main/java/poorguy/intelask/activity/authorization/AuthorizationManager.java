package poorguy.intelask.activity.authorization;

import android.content.Context;

import com.parse.ParseUser;

/**
 * Created by nguyentuananh on 13/6/15.
 */
public class AuthorizationManager {
    private Context mContext;
    private ParseUser curUser;

    public AuthorizationManager(Context context) {
        mContext = context;
    }

    public ParseUser getCurrentUser() {
        if (curUser == null)
            curUser = ParseUser.getCurrentUser();
        return curUser;
    }

    public boolean isSignIn() {
        return this.getCurrentUser() != null;
    }

    public void signUp(String username, String password, String repassword, String email) {
        ParseUser user = new ParseUser();

        String report = validate(username, password, repassword, email);

        // TODO: create user here
    }

    public void signOut() {
        // TODO: signout here
    }

    public void signIn(String email, String password) {
        // TODO: signin here
    }

    private String validate(String username, String password, String repassword, String email) {
        String report = null;
        if ( !password.matches("\\w+") || password.length() < 5 )
            report = "Password must be at least 5 characters and contains only 0-9, a-z, A-Z.";
        else if ( password != repassword )
            report = "Confirm password mismatches.";
        // TODO: add more check here

        return report;
    }

    private boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public interface AuthorizationListener {
        public void DataError(String report);
        public void NetworkError(String report);
        public void Success();
    }

}
