package poorguy.intelask.main;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;
import java.util.List;

import poorguy.intelask.authorization.AuthorizationManager;

/**
 * Created by nguyentuananh on 14/6/15.
 */
public class Question {
    private final static String TAG = "Question";

    public final static String TALBE = "Question";
    public final static String KEY_ID = "objectId";
    public final static String KEY_QUESTION = "question";
    public final static String KEY_USER = "user";
    public final static String KEY_LAST_ANSWER = "lastAnswer";
    public final static String KEY_CREATED_AT = "createdAt";
    public final static String KEY_NUM_ANSWER = "numAnswer";
    public final static String KET_UPDATED_AT = "updatedAt";

    private ParseObject question;
    private ParseUser user = null;

    public Question(ParseObject question) {
        this.question = question;
    }

    public Question() {
        this.question = new ParseObject(TALBE);
    }

    public String getObjectId() {
        return question.getObjectId();
    }

    public Date getUpdatedAt() {
        return question.getUpdatedAt();
    }

    public int getInt(String attr) {
        return question.getInt(attr);
    }

    public void getUser(final AuthorizationManager.GetUserCallBack callback) {
        if (this.user != null) {
            callback.success(this.user);
            return;
        }
        this.question.getParseUser(KEY_USER).fetchIfNeededInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if ( e == null ) {
                    Question.this.user = (ParseUser) parseObject;
                    callback.success(  Question.this.user );
                } else
                    callback.error(AuthorizationManager.NETWORK_ERROR);
            }
        });
    }

//    public String getUsername() {
//        //return this.question.getParseUser(KEY_USER).getString(AuthorizationManager.KEY_NAME);
//        return this.question.getParseUser(KEY_USER).getUsername();
//    }

    public String getString(String attr) {
        return question.getString(attr);
    }

    public ParseObject getParseObject() {
        return this.question;
    }

    public void put(String attr, String value) {
        this.question.put(attr, value);
    }

    public void put(String attr, ParseObject value) {
        this.question.put(attr, value);
    }

    public void put(String attr, Date value) {
        this.question.put(attr, value);
    }

    public void save(final QuestionManager.OnSaveQuestionListener callback) {
        this.question.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if ( e == null )
                    callback.success(Question.this);
                else
                    callback.error(QuestionManager.NETWORK_ERROR);
            }
        });
    }

}
