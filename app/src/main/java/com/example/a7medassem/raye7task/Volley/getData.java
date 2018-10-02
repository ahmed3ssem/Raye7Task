
/**
 * Handle the data form API using VOLLey web services
 */

package com.example.a7medassem.raye7task.Volley;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a7medassem.raye7task.Activity.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class getData {

    private Context context;
    private String URL;
    public static List<String> urls= new ArrayList<>();

    public getData(Context context,String URL) {
        this.context = context;
        this.URL = URL;
    }

    public void getInformation()
    {

        RequestQueue queue;
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    Log.v("Your Array Response", response);


                    fetchData(response);

                } else {
                    Log.e("Your Array Response", "Data Null");
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);

            }
        });
        queue = Volley.newRequestQueue(context);
        queue.add(request);
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }

    private void fetchData(String response)
    {
        /* Parse json contact */
        JSONObject mainObject = null;
        try {
            mainObject = new JSONObject(response);
            JSONArray articleArray = mainObject.getJSONArray("articles");
            for(int i=0;i<articleArray.length();i++)
            {
                JSONObject articleObj=articleArray.getJSONObject(i);
                String Image = articleObj.getString("urlToImage");
                String Date = articleObj.getString("publishedAt");
                urls.add(articleObj.getString("url"));
                JSONObject secondobj = articleArray.getJSONObject(i);
                JSONObject source=secondobj.getJSONObject("source");
                String Name = source.getString("name");
                MainActivity activity = new MainActivity();
                activity.showData(Name , Date , Image);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
