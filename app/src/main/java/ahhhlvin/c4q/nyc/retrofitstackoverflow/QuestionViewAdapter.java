package ahhhlvin.c4q.nyc.retrofitstackoverflow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alvin2 on 12/6/16.
 */

class QuestionViewAdapter extends RecyclerView.Adapter<QuestionViewAdapter.QuestionViewHolder> {

    private Context context;
    private ArrayList<Question> questionsList;

    QuestionViewAdapter (Context context, ArrayList<Question> questionsList) {
            this.context = context;
            this.questionsList = questionsList;
    }


    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, null);
        return new QuestionViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        if (questionsList.size() > 0) {
            holder.textViewTop.setText(questionsList.get(position).toString());
            holder.textViewBottom.setText(questionsList.get(position).getDate());
        }
    }

    @Override
        public long getItemId(int i) {
            return 0;
        }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    // FOR LISTVIEW IMPLEMENTATION:

//
//    @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            ViewHolder viewHolder;
//            final View resultView;
//
//            if (view == null) {
//
//                viewHolder = new ViewHolder();
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view = inflater.inflate(
//                        R.layout.list_item_layout, viewGroup, false);
//
//                viewHolder.textViewTop = (TextView) view.findViewById(R.id.text_view_1);
//                viewHolder.textViewBottom = (TextView) view.findViewById(R.id.text_view_2);
//
//                resultView = view;
//                view.setTag(viewHolder);
//
//            } else {
//                viewHolder = (ViewHolder) view.getTag();
//                resultView = view;
//            }
//
//            if (questionsList != null && questionsList.size() > 0) {
//                viewHolder.textViewTop.setText(questionsList.get(i).toString());
//                viewHolder.textViewBottom.setText(questionsList.get(i).getDate());
//            }
//
//            return resultView;
//        }



    // VIEWHOLDER INNER CLASS

    class QuestionViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        TextView textViewTop;
        TextView textViewBottom;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            // TODO: Remember to set OnClickListener on the item view to receive gesture and respond
            itemView.setOnClickListener(this);

            textViewTop = (TextView) itemView.findViewById(R.id.text_view_1);
            textViewBottom = (TextView) itemView.findViewById(R.id.text_view_2);
        }

        @Override
        public void onClick(View view) {
            Intent webViewIntent = new Intent(view.getContext(), WebViewActivity.class);
            webViewIntent.putExtra("question_link", questionsList.get(getAdapterPosition()).getLink());
            view.getContext().startActivity(webViewIntent);
        }
    }

}
