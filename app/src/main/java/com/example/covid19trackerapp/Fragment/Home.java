package com.example.covid19trackerapp.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19trackerapp.ConnectionCheck;
import com.example.covid19trackerapp.CountryData;
import com.example.covid19trackerapp.CountryDataAdapter;
import com.example.covid19trackerapp.MainScreen;
import com.example.covid19trackerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }
    private RequestQueue requestQueue;
    private ArrayList<CountryData> countryList;
    private CountryDataAdapter countryDataAdapter;

    private TextView tv,tvTotalCases,tvRecovered,tvDeath,tvActive,tvMild,tvCritical,tvNewCases,tvNewDeath,tvPopulation,tvTest,tvAffectedCountries;
    private String totalCases,activeCase,recoveredCase,todayCases,todayDeath,totalDeath,totalTest,affectedCountries,population,critical;
    ProgressBar progressBar;

    private SwipeRefreshLayout pullToRefresh;

    private RecyclerView recyclerView;
    private LinearLayout homeList;
    private CardView cardViewOfCountryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

       // tv=view.findViewById(R.id.tv);
        tvTotalCases=view.findViewById(R.id.tvTotalCases);
        tvRecovered=view.findViewById(R.id.tvRecovered);
        tvDeath=view.findViewById(R.id.tvDeath);
        progressBar=view.findViewById(R.id.progressBar);
        tvActive=view.findViewById(R.id.tvActive);
        tvMild=view.findViewById(R.id.tvMild);
        tvCritical=view.findViewById(R.id.tvCritical);
        tvNewCases=view.findViewById(R.id.tvNewCases);
        tvNewDeath=view.findViewById(R.id.tvNewDeath);
        tvPopulation=view.findViewById(R.id.tvPopulation);
        tvTest=view.findViewById(R.id.tvTest);
        tvAffectedCountries=view.findViewById(R.id.tvAffectedCountries);

        cardViewOfCountryList=view.findViewById(R.id.countryListCardView);

        pullToRefresh=view.findViewById(R.id.pullToRefresh);

       /* recyclerView=view.findViewById(R.id.recyclerView);
        countryList=new ArrayList<>();

        countryDataAdapter = new CountryDataAdapter(getContext(), countryList, recyclerView);*/

        progressBar.setVisibility(View.VISIBLE);

        requestQueue = Volley.newRequestQueue(getContext());

        addData();

        pullToRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                progressBar.setVisibility(View.VISIBLE);
                addData();
                pullToRefresh.setRefreshing(false);
            }
        });
        if (pullToRefresh.isRefreshing()) {
            pullToRefresh.setRefreshing(false);
        }

      /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(countryDataAdapter);
*/
      cardViewOfCountryList.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Fragment fr=new AllCountryList();
              getFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).addToBackStack(null).commit();

          }
      });
        return view;
    }

    private void addData() {
        if(!ConnectionCheck.checkConnection(getContext())) {
            progressBar.setVisibility(View.GONE);
            AlertDialog.Builder dailog =new AlertDialog.Builder(getContext());
            dailog.setMessage(getString(R.string.connectionAlert));
            dailog.setCancelable(false);
            dailog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dailog.create().show();
        }

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

                            tvNewCases.setText(todayCases);
                            tvNewDeath.setText(todayDeath);

                            tvPopulation.setText(population);
                            tvTest.setText(totalTest);

                            tvAffectedCountries.setText(affectedCountries);
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

       /* String url2 = "https://disease.sh/v2/countries";
        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        int length=response.length();
                        try {
                            for(int i=0;i<length;i++){
                               JSONObject obj= (JSONObject) response.get(i);
                               JSONObject countryInfo=obj.getJSONObject("countryInfo");
                               final CountryData countryData=new CountryData();
                               countryData.setCountry(obj.getString("country"));
                               countryData.setCountryCases(obj.getString("cases"));
                               countryData.setCountryDeaths(obj.getString("deaths"));
                               countryData.setCountryFlag(countryInfo.getString("flag"));
                               countryData.setCountryTodayCases(obj.getString("todayCases"));
                               countryData.setCountryTodayDeath(obj.getString("todayDeaths"));
                               countryData.setCountryRecovered(obj.getString("recovered"));
                               countryData.setCountryActive(obj.getString("active"));
                               countryData.setCountryCritical(obj.getString("critical"));
                               countryData.setCountryTests(obj.getString("tests"));
                               countryData.setCountryPopulation(obj.getString("population"));
                               countryList.add(countryData);
                               countryDataAdapter.notifyItemInserted(countryList.size()-1);
                            }
                            progressBar.setVisibility(View.GONE);
                            //  tv.setText(String.valueOf(response.get(0)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressBar.setVisibility(View.VISIBLE);

                    }
                });
        requestQueue.add(jsonArrayRequest);*/


    }
}
