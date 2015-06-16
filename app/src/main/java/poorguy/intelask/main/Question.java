package poorguy.intelask.main;

import com.parse.ParseObject;

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

    public String getObjectId() {
        return question.getObjectId();

    }


    public String getString(String attr) {
        return question.getString(attr);
    }

}
