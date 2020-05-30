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

public class StateDataAdapter  extends RecyclerView.Adapter<StateDataAdapter.ViewHolder> {

    private Context context;
    private ArrayList<StateData> stateDataArrayList;
    private View recyclerView;

    public StateDataAdapter(Context context, ArrayList<StateData> stateDataArrayList, View view) {
        this.context=context;
        this.stateDataArrayList=stateDataArrayList;
        this.recyclerView=view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.state_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final StateData stateData=stateDataArrayList.get(position);
        final TextView state=holder.tvState;
        final TextView totalCases=holder.tvTotalCase;
        final TextView totalDeath=holder.tvTotalDeath;
        final TextView recovered=holder.tvRecovered;
        final TextView active=holder.tvActiveCases;

        state.setText(stateData.getState());
        totalCases.setText(stateData.getTotal());
        totalDeath.setText(stateData.getDeath());
        recovered.setText(stateData.getRecovered());
        active.setText(stateData.getActive());

      /*  countryLayout.setOnClickListener(new View.OnClickListener() {
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
*/
    }

    @Override
    public int getItemCount() {
        return stateDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvState,tvTotalCase,tvTotalDeath,tvActiveCases,tvRecovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvState=itemView.findViewById(R.id.tvState);
            tvTotalCase=itemView.findViewById(R.id.tvStateTotalCase);
            tvTotalDeath=itemView.findViewById(R.id.tvStateDeath);
            tvActiveCases=itemView.findViewById(R.id.tvStateActive);
            tvRecovered=itemView.findViewById(R.id.tvStateRecovered);
        }
    }
}
