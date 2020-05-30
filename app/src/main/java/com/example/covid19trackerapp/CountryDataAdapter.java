package com.example.covid19trackerapp;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19trackerapp.Fragment.Country;
import com.example.covid19trackerapp.Fragment.Home;

import java.util.ArrayList;

public class CountryDataAdapter  extends RecyclerView.Adapter<CountryDataAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CountryData> countryDataArrayList;
    private View recyclerView;

    public CountryDataAdapter(Context context, ArrayList<CountryData> countryDataArrayList, View view) {
       this.context=context;
       this.countryDataArrayList=countryDataArrayList;
       this.recyclerView=view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.all_country_list_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final CountryData countryData=countryDataArrayList.get(position);
       final TextView countryName=holder.countryName;
       final TextView totalCases=holder.totalCases;
       final TextView totalDeath=holder.totalDeath;
       final LinearLayout countryLayout=holder.countryLayout;

       countryName.setText(countryData.getCountry());
       totalCases.setText(countryData.getCountryCases());
       totalDeath.setText(countryData.getCountryDeaths());

       countryLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Fragment fr=new Country();
               Bundle bundle=new Bundle();
               bundle.putString("screenName","CountryList");
               bundle.putString("country",countryData.getCountry());
               bundle.putString("active",countryData.getCountryActive());
               bundle.putString("cases",countryData.getCountryCases());
               bundle.putString("critical",countryData.getCountryCritical());
               bundle.putString("deaths",countryData.getCountryDeaths());
               bundle.putString("flag",countryData.getCountryFlag());
               bundle.putString("population",countryData.getCountryPopulation());
               bundle.putString("recovered",countryData.getCountryRecovered());
               bundle.putString("tests",countryData.getCountryTests());
               bundle.putString("todayCases",countryData.getCountryTodayCases());
               bundle.putString("todayDeaths",countryData.getCountryTodayDeath());
               fr.setArguments(bundle);
               ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).addToBackStack(null).commit();
           }
       });

    }

    @Override
    public int getItemCount() {
        return countryDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryName,totalCases,totalDeath;
        LinearLayout countryLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.countryName);
            totalCases=itemView.findViewById(R.id.totalCases);
            totalDeath=itemView.findViewById(R.id.totalDeath);
            countryLayout=itemView.findViewById(R.id.countryLayout);
        }
    }
}
