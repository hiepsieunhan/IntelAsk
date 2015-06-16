package poorguy.intelask.main;

import com.parse.ParseObject;

/**
 * Created by nguyentuananh on 16/6/15.
 */
public class Answer {
    public static final String TABLE = "Answer";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_UPDATED_AT = "updatedAt";

    private final ParseObject answer;

    public Answer(ParseObject answer) {
        this.answer = answer;
    }
}
