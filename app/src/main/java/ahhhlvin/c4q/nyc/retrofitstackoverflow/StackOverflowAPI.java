package ahhhlvin.c4q.nyc.retrofitstackoverflow;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alvin2 on 12/5/16.
 */

public interface StackOverflowAPI {
    // The string contains the default part of the parameters in the request URL that won't be modified
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    // The '@Query' string specifies the name of the desired parameter key along with the name of the parameter value argument to be passed in
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}
