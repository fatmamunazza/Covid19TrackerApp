package com.example.covid19trackerapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid19trackerapp.R;
import com.squareup.picasso.Picasso;

public class Country extends Fragment {

    public Country() {
        // Required empty public constructor
    }
    private TextView tv,tvTotalCases,tvRecovered,tvDeath,tvActive,tvMild,tvCritical,tvNewCases,tvNewDeath,tvPopulation,tvTest,countryName;
    private String country,flagUrl,totalCases,activeCase,recoveredCase,todayCases,todayDeath,totalDeath,totalTest,affectedCountries,population,critical;
    private ImageView flag;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_country, container, false);

        countryName=view.findViewById(R.id.countryName);
        flag=view.findViewById(R.id.flag);
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

        progressBar=view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

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

        countryName.setText(country);
        tvTotalCases.setText(totalCases);
        tvRecovered.setText(recoveredCase);
        tvDeath.setText(totalDeath);
        tvActive.setText(activeCase);
        tvPopulation.setText(population);
        tvTest.setText(totalTest);
        tvNewCases.setText(todayCases);
        tvNewDeath.setText(todayDeath);
        tvCritical.setText(critical);

        String mild=String.valueOf(Integer.parseInt(activeCase)-Integer.parseInt(critical));

        tvMild.setText(mild);

        Picasso.get()
                .load(flagUrl)
                .into(flag,
                        new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }
                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                            }
                        });

        return view;
    }
}
