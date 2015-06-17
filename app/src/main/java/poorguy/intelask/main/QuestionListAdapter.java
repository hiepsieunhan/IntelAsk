package poorguy.intelask.main;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import poorguy.techask.R;

/**
 * Created by nguyentuananh on 16/6/15.
 */
public class QuestionListAdapter extends ArrayAdapter<Question> {
    private static final String TAG = "QuestionListAdapter";
    private Context mContext;
    private int layoutId;
    private List quesions;
    public QuestionListAdapter(Context ctx, int layoutId, List questions ) {
        super(ctx, layoutId, questions);
        this.mContext = ctx;
        this.layoutId = layoutId;
        this.quesions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "position: " + position);
        Question question = (Question) quesions.get(position);
        ViewHolder holder = null;
        if (convertView == null ) {
            LayoutInflater inflater = ( (Activity) this.mContext ).getLayoutInflater();
            convertView = inflater.inflate(this.layoutId, parent, false);

            holder = new ViewHolder();
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.questionDate = (TextView) convertView.findViewById(R.id.question_date);
            holder.question = (TextView) convertView.findViewById(R.id.question);
            holder.numAnswer = (TextView) convertView.findViewById(R.id.numAnswer);
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();


        String convertDate = DateUtils.getRelativeTimeSpanString(
                question.getUpdatedAt().getTime(),
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        holder.author.setText(question.getString(Question.KEY_USER));
        holder.questionDate.setText(convertDate);
        holder.question.setText(question.getString(Question.KEY_QUESTION));
        holder.numAnswer.setText( question.getInt(Question.KEY_NUM_ANSWER)+ " " + this.mContext.getResources().getString(R.string.num_answer) );

        return convertView;
    }

    void addQuestions(List<Question> newQuestions) {
        for(Question question : newQuestions)
            this.quesions.add(question);
        Log.d(TAG, new Integer(newQuestions.size()).toString());
        notifyDataSetChanged();
    }

    static class ViewHolder {
        public TextView author, questionDate, question, numAnswer;
    }
}
