package poorguy.intelask.main;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by nguyentuananh on 14/6/15.
 */
public class QuestionManager {
    private final static int RETRIEVE_LIMIT = 20;
    private int numQuestion;
    private ParseUser currentUser;

    public QuestionManager(ParseUser currentUser) {
        this.currentUser = currentUser;
        resetQuestion();
    }

    public void resetQuestion() {
        numQuestion = 0;
    }

    public void retrieveNext() {
        // TODO: retrieve Next 20 question here
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.setSkip(numQuestion);
        query.addDescendingOrder("lastAnswer");
        query.setLimit(RETRIEVE_LIMIT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if ( e != null ) {

                }
            }
        });
    }



}
