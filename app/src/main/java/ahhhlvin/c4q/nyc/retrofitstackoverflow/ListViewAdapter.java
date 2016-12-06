package ahhhlvin.c4q.nyc.retrofitstackoverflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alvin2 on 12/6/16.
 */

class ListViewAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Question> questionsList;

        private class ViewHolder {
            TextView textViewTop;
            TextView textViewBottom;
        }

        ListViewAdapter (Context context, ArrayList<Question> questionsList) {
            this.context = context;
            this.questionsList = questionsList;
        }

        @Override
        public int getCount() {
            return questionsList.size();
        }

        @Override
        public Object getItem(int i) {
            return questionsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;
            final View resultView;

            if (view == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(
                        R.layout.list_item_layout, viewGroup, false);

                viewHolder.textViewTop = (TextView) view.findViewById(R.id.text_view_1);
                viewHolder.textViewBottom = (TextView) view.findViewById(R.id.text_view_2);

                resultView = view;
                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();
                resultView = view;
            }

            if (questionsList != null && questionsList.size() > 0) {
                viewHolder.textViewTop.setText(questionsList.get(i).toString());
                viewHolder.textViewBottom.setText(questionsList.get(i).getDate());
            }

            return resultView;
        }
}
