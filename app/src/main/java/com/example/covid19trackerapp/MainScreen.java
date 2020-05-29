package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainScreen extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView tvTotalCases,tvRecovered,tvDeath,tvActive,tvMild,tvCritical;
    private String totalCases,activeCase,recoveredCase,todayCases,todayDeath,totalDeath,totalTest,affectedCountries,population,critical;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        tvTotalCases=findViewById(R.id.tvTotalCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvDeath=findViewById(R.id.tvDeath);
        progressBar=findViewById(R.id.progressBar);
        tvActive=findViewById(R.id.tvActive);
        tvMild=findViewById(R.id.tvMild);
        tvCritical=findViewById(R.id.tvCritical);

        progressBar.setVisibility(View.VISIBLE);

        requestQueue = Volley.newRequestQueue(MainScreen.this);

        String url = "https://disease.sh/v2/all";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            totalCases=response.getString("cases");
                            activeCase=response.getString("active");
                            critical=response.getString("critical");
                            totalDeath=response.getString("deaths");
                            totalTest=response.getString("tests");
                            todayCases=response.getString("todayCases");
                            todayDeath=response.getString("todayDeaths");
                            population=response.getString("population");
                            recoveredCase=response.getString("recovered");
                            affectedCountries=response.getString("affectedCountries");

                            tvTotalCases.setText(totalCases);
                            tvRecovered.setText(recoveredCase);
                            tvDeath.setText(totalDeath);

                            tvCritical.setText(critical);
                            tvActive.setText(activeCase);
                            String mild=String.valueOf(Integer.parseInt(activeCase)-Integer.parseInt(critical));
                            tvMild.setText(mild);

                            progressBar.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}
