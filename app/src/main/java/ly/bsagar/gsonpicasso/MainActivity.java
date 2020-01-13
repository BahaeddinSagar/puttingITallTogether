package ly.bsagar.gsonpicasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // this URL contains list of Harrypotter movies
    String url = "https://android-course-f2826.firebaseio.com/.json?fbclid=IwAR1sGNtBTMAafOF9hG8gUFansj-90RWD_dpLncNhlHK2B8qgrkE34EqD7Xc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showList(View view) {
       makerequest(url);
    }
    // makes https request and displays result in a ListView
    private void makerequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //make empty arraylist to hold movies
                        ArrayList<MovieClass> moviesList = new ArrayList<>();
                        try {
                            // convert string rsponse to arraylist of MovieClass
                             moviesList = parseJson(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ListView listView = findViewById(R.id.myList);
                        // make arrayAdapter for MovieClass with item_layout as
                        // layout for each itemView
                        MovieArrayAdapter arrayAdapter = new MovieArrayAdapter(
                                MainActivity.this,R.layout.item_layout
                                ,moviesList);
                        listView.setAdapter(arrayAdapter);
                        // need array to be final if to be used inside onItemclick listener
                        final ArrayList<MovieClass> finalMoviesList = moviesList;
                        // when the user clicks on an item he goes to another activity with details on that movie
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                // make an intent and add pass the movies details with it
                                Intent intent = new Intent(MainActivity.this , detailedActivity.class);
                                intent.putExtra("title", finalMoviesList.get(i).name );
                                intent.putExtra("desc", finalMoviesList.get(i).description );
                                intent.putExtra("year", finalMoviesList.get(i).year );
                                intent.putExtra("ImageURL", finalMoviesList.get(i).ImageURL );
                                intent.putExtra("URL", finalMoviesList.get(i).URL );
                                startActivity(intent);
                            }
                        });


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //make http request
        queue.add(stringRequest);
    }

    // convert string rsponse to arraylist of MovieClass
    private ArrayList<MovieClass> parseJson(String jsonResponce)
            throws JSONException {
        // create JsonObject from string
        JSONObject jObject = new JSONObject(jsonResponce);
        // create JSONarray from "Data" key
        JSONArray jArray = jObject.getJSONArray("Data");
        Gson gson = new Gson();
        // make empty arrayList to hold movies
        ArrayList<MovieClass> movieClassArrayList = new ArrayList<>();
        //loop through JSONarray and fill arrayList
        for (int i = 0 ; i < jArray.length() ; i++){
            MovieClass m = gson.fromJson(String.valueOf(jArray.get(i))
                    , MovieClass.class);
            movieClassArrayList.add(m);
        }
        return movieClassArrayList;

    }
}