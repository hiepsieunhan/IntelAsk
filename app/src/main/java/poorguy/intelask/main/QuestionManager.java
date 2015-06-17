package poorguy.intelask.main;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nguyentuananh on 14/6/15.
 */
public class QuestionManager {
    private final static String TAG = "QuestionManager";
    private final static int RETRIEVE_LIMIT = 20;
    private int numQuestion;
    private ParseUser currentUser;
    public static final int NETWORK_ERROR = 1;

    public QuestionManager(ParseUser currentUser) {
        this.currentUser = currentUser;
        resetQuestion();
    }

    public void resetQuestion() {
        numQuestion = 0;
    }

    public void retrieveNext(final OnRetrieveQuestionListener callback) {
        // TODO: retrieve Next 20 question here // done
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Question.TALBE);
        query.setSkip(numQuestion);
        query.addDescendingOrder(Question.KEY_LAST_ANSWER);
        query.setLimit(RETRIEVE_LIMIT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                List<Question> questions = new ArrayList<Question>();
                if (e == null) {
                    for (ParseObject question : list) {
                        questions.add(new Question(question));
                        numQuestion++;
                    }
                    Log.d(TAG, new Integer(list.size()).toString() );
                    callback.success(questions);
                } else
                    callback.error(NETWORK_ERROR);
            }
        });
    }

    public void addNewQuestion(String questionStr, OnSaveQuestionListener callback) {
        // TODO: add new question here
        Question question = new Question();
        question.put(Question.KEY_QUESTION, questionStr);
        question.put(Question.KEY_USER, currentUser);
        question.put(Question.KEY_LAST_ANSWER, new Date());
        question.save(callback);
    }

    public interface OnRetrieveQuestionListener {
        public void success(List<Question> questions);
        public void error(int code);
    }

    public interface OnSaveQuestionListener {
        public void success(Question question);
        public void error(int code);
    }



}
