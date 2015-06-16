package poorguy.intelask.main;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyentuananh on 16/6/15.
 */
public class AnswerManager {

    public final static int NETWORK_ERROR = 1;

    private final static int RETRIEVE_LIMIT = 20;
    private final ParseUser currentUser;
    private final Question question;
    private int numAnswer = 0;

    public AnswerManager(ParseUser currentUser, Question question) {
        this.currentUser = currentUser;
        this.question = question;
    }

    public void resetAnswer() {
        this.numAnswer = 0;
    }

    public void retrieveNext(final OnRetrieveAnswerListener callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Answer.TABLE);
        query.whereEqualTo(Answer.KEY_QUESTION, question.getObjectId());
        query.setLimit(RETRIEVE_LIMIT);
        query.setSkip(numAnswer);
        query.orderByDescending(Answer.KEY_UPDATED_AT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if ( e == null ) {
                    List<Answer> answers = new ArrayList<Answer>();
                    for(ParseObject answer : list) {
                        answers.add(new Answer(answer));
                        numAnswer++;
                    }
                    callback.success(answers);
                } else
                    callback.error(NETWORK_ERROR);
            }
        });
    }

    public interface OnRetrieveAnswerListener {
        public void success(List<Answer> answers);
        public void error(int code);
    }
}
