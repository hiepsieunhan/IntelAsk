package poorguy.intelask.activity.authorization;

import android.content.Context;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by nguyentuananh on 13/6/15.
 */
public class AuthorizationManager {
    private ParseUser curUser;

    public static final int INVALID_PASSWORD = 1;
    public static final int INVALID_USERNAME = 2;
    public static final int INVALID_EMAIL = 3;
    public static final int CONFIRM_PASSWORD_MISMATCH = 4;
    public static final int NETWORK_ERROR = 5;
    public static final int RESULT_OK = 0;
    public static final int ACCOUNT_NOT_FOUND = 6;

    public AuthorizationManager() {
    }

    public ParseUser getCurrentUser() {
        if (curUser == null)
            curUser = ParseUser.getCurrentUser();
        return curUser;
    }

    public boolean isSignIn() {
        return this.getCurrentUser() != null;
    }

    public void signUp(String username, String password, String repassword, String email, final SignupCallback callback) {

        int code = validate(username, password, repassword, email);
        if ( code != RESULT_OK ) {
            callback.error(code);
            return;
        }

        // TODO: create user here // done
        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.put("name", username);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                    callback.success(RESULT_OK);
                else
                    callback.error(NETWORK_ERROR);
            }
        });

    }

    public void signOut(final SignoutCallback callback) {
        // TODO: signout here // done
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    callback.error(NETWORK_ERROR);
                else {
                    curUser = null;
                    callback.error(RESULT_OK);
                }
            }
        });
    }

    public void signIn(String email, String password, final SigninCallback callback) {
        // TODO: signin here // done
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null && parseUser != null) {
                    curUser = parseUser;
                    callback.success(RESULT_OK);
                } else if (parseUser == null)
                    callback.error(ACCOUNT_NOT_FOUND);
                else callback.error(NETWORK_ERROR);
            }
        });
    }

    private int validate(String username, String password, String repassword, String email) {

        // TODO: add check here
        if ( email != null && !isValidEmail(email) )
            return INVALID_EMAIL;
        if ( username != null && !isValidUsername(username) )
            return INVALID_USERNAME;
        if ( password != null && !isValidPassword(password) )
            return INVALID_PASSWORD;
        if ( repassword != null && !isValidConfirmPassword(password, repassword) )
            return CONFIRM_PASSWORD_MISMATCH;
        return RESULT_OK;

    }

    private boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidPassword( String password ) {
        return password.matches("\\w+") && password.length() >= 5;
    }

    private boolean isValidUsername( String username ) {
        return username.matches("\\w+") && username.length() >= 4 && username.length() <= 20;
    }

    private boolean isValidConfirmPassword( String password, String rePassword ) {
        return password.equals(rePassword);
    }

    public interface SignupCallback {
        public void error(int code);
        public void success(int code);
    }

    public interface SigninCallback {
        public void error(int code);
        public void success(int code);
    }

    public interface SignoutCallback {
        public void error(int code);
        public void success(int code);
    }

}
