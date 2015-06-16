package poorguy.intelask.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import poorguy.techask.R;

/**
 * Created by nguyentuananh on 16/6/15.
 */
public class QuestionListAdapter extends ArrayAdapter<Question> {
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
        ViewHolder holder = null;
        if (convertView.getTag() == null ) {
            LayoutInflater inflater = ( (Activity) this.mContext ).getLayoutInflater();
            convertView = inflater.inflate(this.layoutId, parent, false);

            holder = new ViewHolder();
            holder.author = (TextView) convertView.findViewById(R.id.author);
            

        } else
            holder = (ViewHolder) convertView.getTag();
        return convertView;
    }

    static class ViewHolder {
        public TextView author, questionDate, question, numQuesion;
    }
}
