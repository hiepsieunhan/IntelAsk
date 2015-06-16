package poorguy.intelask.main;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Date;

/**
 * Created by nguyentuananh on 14/6/15.
 */
public class Question {
    public final static String TALBE = "Question";
    public final static String KEY_ID = "objectId";
    public final static String KEY_QUESTION = "question";
    public final static String KEY_USER = "user";
    public final static String KEY_LAST_ANSWER = "lastAnswer";
    public final static String KEY_CREATED_AT = "createdAt";
    public final static String KET_UPDATED_AT = "updatedAt";

    private ParseObject question;

    public Question(ParseObject question) {
        this.question = question;
    }

    public Question() {
        this.question = new ParseObject(TALBE);
    }

    public String getObjectId() {
        return question.getObjectId();

    }


    public String getString(String attr) {
        return question.getString(attr);
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
                if ( e != null )
                    callback.success(Question.this);
                else
                    callback.error(QuestionManager.NETWORK_ERROR);
            }
        });
    }

}
