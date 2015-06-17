package poorguy.intelask.main;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import poorguy.techask.R;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private QuestionManager questionManager;
    private ListView mListView;
    private QuestionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        questionManager = new QuestionManager(new ParseUser());
        mListView = (ListView) findViewById(R.id.question_list);
        adapter = new QuestionListAdapter(this, R.layout.question_item, new ArrayList<Question>());

        mListView.setAdapter(adapter);

        Log.d(TAG, "HERE");

        questionManager.resetQuestion();
        questionManager.retrieveNext(new QuestionManager.OnRetrieveQuestionListener() {
            @Override
            public void success(List<Question> questions) {
                adapter.addQuestions(questions);
            }

            @Override
            public void error(int code) {
                // TODO: handle network error
            }
        });

    }

}
