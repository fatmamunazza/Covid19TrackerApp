package com.example.covid19trackerapp.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19trackerapp.CountryData;
import com.example.covid19trackerapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Country extends Fragment {

    public Country() {
        // Required empty public constructor
    }
    private TextView tv,tvTotalCases,tvRecovered,tvDeath,tvActive,tvMild,tvCritical,tvNewCases,tvNewDeath,tvPopulation,tvTest,countryName;
    private String screenName,country,flagUrl,totalCases,activeCase,recoveredCase,todayCases,todayDeath,totalDeath,totalTest,affectedCountries,population,critical;
    private ImageView flag,map;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    private LinearLayout affectedStatesTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_country, container, false);

        requestQueue = Volley.newRequestQueue(getContext());


        countryName=view.findViewById(R.id.countryName);
        flag=view.findViewById(R.id.flag);
        map=view.findViewById(R.id.map);
        tvTotalCases=view.findViewById(R.id.tvTotalCases);
        tvRecovered=view.findViewById(R.id.tvRecovered);
        tvDeath=view.findViewById(R.id.tvDeath);
        tvActive=view.findViewById(R.id.tvActive);
        tvMild=view.findViewById(R.id.tvMild);
        tvCritical=view.findViewById(R.id.tvCritical);
        tvNewCases=view.findViewById(R.id.tvNewCases);
        tvNewDeath=view.findViewById(R.id.tvNewDeath);
        tvPopulation=view.findViewById(R.id.tvPopulation);
        tvTest=view.findViewById(R.id.tvTest);

        affectedStatesTab=view.findViewById(R.id.affectedStatesTab);

        progressBar=view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        screenName=getArguments().getString("screenName");

        if(screenName.equalsIgnoreCase("CountryList")){
            country=getArguments().getString("country");
            flagUrl=getArguments().getString("flag");
            totalCases=getArguments().getString("cases");
            totalDeath=getArguments().getString("deaths");
            recoveredCase=getArguments().getString("recovered");
            activeCase=getArguments().getString("active");
            todayCases=getArguments().getString("todayCases");
            todayDeath=getArguments().getString("todayDeaths");
            critical=getArguments().getString("critical");
            totalTest=getArguments().getString("tests");
            population=getArguments().getString("population");
            addData();
        }
        else{
            country=getArguments().getString("country");
            String url = "https://disease.sh/v2/countries/" + country;
            JsonObjectRequest
                    jsonObjectRequest
                    = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject obj) {

                            try {
                                JSONObject countryInfo=obj.getJSONObject("countryInfo");

                                flagUrl=countryInfo.getString("flag");
                                totalCases=obj.getString("cases");
                                totalDeath=obj.getString("deaths");
                                recoveredCase=obj.getString("recovered");
                                activeCase=obj.getString("active");
                                todayCases=obj.getString("todayCases");
                                todayDeath=obj.getString("todayDeaths");
                                critical=obj.getString("critical");
                                totalTest=obj.getString("tests");
                                population=obj.getString("population");

                                addData();

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
            requestQueue.add(jsonObjectRequest);
        }

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr=new AllStateList();
                getFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
            }
        });
        return view;
    }

    private void addData() {
        String strOfCountry=country.toUpperCase();

        countryName.setText(strOfCountry);
        tvTotalCases.setText(totalCases);
        tvRecovered.setText(recoveredCase);
        tvDeath.setText(totalDeath);
        tvActive.setText(activeCase);
        tvPopulation.setText(population);
        tvTest.setText(totalTest);
        tvNewCases.setText(todayCases);
        tvNewDeath.setText(todayDeath);
        tvCritical.setText(critical);

        String mild = String.valueOf(Integer.parseInt(activeCase) - Integer.parseInt(critical));

        tvMild.setText(mild);

        Picasso.get()
                .load(flagUrl)
                .into(flag,
                        new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                            }
                        });


        if(country.equalsIgnoreCase("INDIA")){
            affectedStatesTab.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(getContext()!=null) {
                    map.setImageDrawable(getActivity().getDrawable(R.drawable.ic_india_map));
                    map.setColorFilter(ContextCompat.getColor(getContext(), R.color.population), android.graphics.PorterDuff.Mode.SRC_IN);

                }
            }
        }
       /* if(country.equalsIgnoreCase("USA")){
            affectedStatesTab.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                map.setImageDrawable(getActivity().getDrawable(R.drawable.ic_usa_map));
                map.setColorFilter(ContextCompat.getColor(getContext(), R.color.totalCase), android.graphics.PorterDuff.Mode.SRC_IN);

            }
        }*/

    }
}
