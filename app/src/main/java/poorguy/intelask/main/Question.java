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

    private String id;
    private String question;
    private String user;
    private Date lastAnswer;
    private Date createdAt;
    private Date updatedAt;

    public Question(ParseObject question) {
        
    }

}