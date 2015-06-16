package poorguy.intelask.main;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Date;

/**
 * Created by nguyentuananh on 16/6/15.
 */
public class Answer {
    public static final String TABLE = "Answer";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_ANSWER = "answer";
    public static final String KEY_USER = "user";
    public static final String KEY_UPDATED_AT = "updatedAt";

    private final ParseObject answer;

    public Answer(ParseObject answer) {
        this.answer = answer;
    }

    public Answer() {
        this.answer = new ParseObject(TABLE);
    }

    public void put(String attr, String value) {
        this.answer.put(attr, value);
    }

    public void put(String attr, ParseObject value) {
        this.answer.put(attr, value);
    }

    public void put(String attr, Date value) {
        this.answer.put(attr, value);
    }

    public void save(final AnswerManager.OnSaveAnswerListener callback) {
        this.answer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if ( e == null )
                    callback.success(Answer.this);
                else
                    callback.error(AnswerManager.NETWORK_ERROR);
            }
        });
    }

}
