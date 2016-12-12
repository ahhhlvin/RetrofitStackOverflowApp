package ahhhlvin.c4q.nyc.retrofitstackoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Query;

public class MainActivity extends AppCompatActivity implements StackOverflowAPI {

    RecyclerView questionReyclerView;
    QuestionViewAdapter questionViewAdapter;
    ArrayList<Question> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionReyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        questionReyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // SETTING CLICK LISTENER FOR LISTVIEW IMPLEMENTATION

//        questionReyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                if(questionViewAdapter.getCount() > 0) {
//                    Intent webViewIntent = new Intent(getApplicationContext(), WebViewActivity.class);
//                    webViewIntent.putExtra("question_link", questionsList.get(i).getLink());
//                    startActivity(webViewIntent);
//                }
//            }
//        });

        questionsList = new ArrayList<>();

        questionViewAdapter =
                new QuestionViewAdapter(this, questionsList);

        questionReyclerView.setAdapter(questionViewAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        StackOverflowAPI stackOverflowAPI = retrofit.create(StackOverflowAPI.class);

        Call<StackOverflowQuestions> call = stackOverflowAPI.loadQuestions("android");

        //asynchronous call
        call.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
                Log.i("RETROFIT REQUEST", "SUCCESS");
                Log.i("RETROFIT REQUEST", response.toString());
                System.out.println();
                questionsList.clear();
                questionsList.addAll(response.body().items);
                questionViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("RETROFIT REQUEST", "FAILED");
            }
        });

        // synchronous call would be with execute, in this case you
        // would have to perform this outside the main thread
        // call.execute()

        // to CANCEL a running request
        // call.cancel();
        // calls can only be used once but you can easily clone them
        //Call<StackOverflowQuestions> c = call.clone();
        //c.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return true;
    }

    // The implemented abstract method from the interface below catches the edge case where no appropriate search tag is input so that will default to returning null

    @Override
    public Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags) {
        return null;
    }
}
