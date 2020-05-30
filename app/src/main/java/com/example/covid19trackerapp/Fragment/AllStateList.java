package com.example.covid19trackerapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.example.covid19trackerapp.CountryData;
import com.example.covid19trackerapp.CountryDataAdapter;
import com.example.covid19trackerapp.R;
import com.example.covid19trackerapp.StateData;
import com.example.covid19trackerapp.StateDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class AllStateList extends Fragment {


    public AllStateList() {
        // Required empty public constructor
    }
    private RequestQueue requestQueue;
    private ArrayList<StateData> stateList;
    private StateDataAdapter stateDataAdapter;

    private String affectedCountries;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayout homeList;

    private AutoCompleteTextView stateSearch;

    private ArrayList<String> stateNameList;
    private String[] mList=new String[]{"afaqyugs" ,"bjabdj","asddd","asdfgh","",""};
    private ArrayAdapter<String> adapter;
    private CardView listOfState;
    private LinearLayout containerState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_state_list, container, false);



        //  pullToRefresh=view.findViewById(R.id.pullToRefresh);
        progressBar=view.findViewById(R.id.progressBar);

        recyclerView=view.findViewById(R.id.recyclerView);

        stateSearch=view.findViewById(R.id.stateSearch);

        containerState=view.findViewById(R.id.containerState);

        stateSearch.setEnabled(false);

        stateList=new ArrayList<>();

        stateDataAdapter = new StateDataAdapter(getContext(), stateList, recyclerView);

        progressBar.setVisibility(View.VISIBLE);

        requestQueue = Volley.newRequestQueue(getContext());

        addData();

     /*   pullToRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
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
        }*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(stateDataAdapter);

        stateSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                recyclerView.setVisibility(View.GONE);
                containerState.setVisibility(View.VISIBLE);
                containerState.removeAllViews();

                String str= ((TextView)view).getText().toString();

                for(int k=0;k<stateNameList.size();k++){
                    if(str.equalsIgnoreCase(stateNameList.get(k))){
                        View view1=getLayoutInflater().inflate(R.layout.state_layout,containerState,false);

                        TextView state=view1.findViewById(R.id.tvState);
                        TextView tvStateTotalCase=view1.findViewById(R.id.tvStateTotalCase);
                        TextView tvStateActive=view1.findViewById(R.id.tvStateActive);
                        TextView tvStateRecovered=view1.findViewById(R.id.tvStateRecovered);
                        TextView tvStateDeath=view1.findViewById(R.id.tvStateDeath);

                        final StateData stateData=stateList.get(k);

                        state.setText(stateData.getState());
                        tvStateTotalCase.setText(stateData.getTotal());
                        tvStateDeath.setText(stateData.getDeath());
                        tvStateRecovered.setText(stateData.getRecovered());
                        tvStateActive.setText(stateData.getActive());

                        containerState.addView(view1);
                    }
                }

            }
        });

        return view;
    }

    private void addData() {

        String url = "https://api.covid19india.org/state_district_wise.json";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        stateNameList=new ArrayList<String>();
                        Iterator<String> keys = response.keys();
                        while (keys.hasNext()) {
                            final StateData stateData=new StateData();

                            String state = keys.next();
                            stateNameList.add(state);

                            stateData.setState(state);

                            ArrayList<String> cities=new ArrayList<String>();
                            ArrayList<Integer> active=new ArrayList<Integer>();
                            ArrayList<Integer> death=new ArrayList<Integer>();
                            ArrayList<Integer> recovered=new ArrayList<Integer>();
                            ArrayList<Integer> total=new ArrayList<Integer>();
                            try {
                                if(response.get(state) instanceof JSONObject) {
                                    JSONObject innerJObject = response.getJSONObject(state);
                                    JSONObject districtData = innerJObject.getJSONObject("districtData");

                                    Iterator<String> district=districtData.keys();
                                    while(district.hasNext()){
                                        String city=district.next();
                                        if(districtData.get(city) instanceof JSONObject) {
                                            JSONObject innerJObject2 = districtData.getJSONObject(city);

                                            int activeCases=innerJObject2.getInt("active");
                                            int deathCases=innerJObject2.getInt("deceased");
                                            int recoveredCases=innerJObject2.getInt("recovered");
                                            int totalCases=innerJObject2.getInt("confirmed");

                                            active.add(activeCases);
                                            death.add(deathCases);
                                            recovered.add(recoveredCases);
                                            total.add(totalCases);

                                        }
                                        cities.add(city);
                                    }
                                    stateData.setActiveCases(active);
                                    stateData.setDeathCases(death);
                                    stateData.setRecoveredCases(recovered);
                                    stateData.setTotalCases(total);
                                    stateData.setCities(cities);


                                }
                                stateList.add(stateData);

                                stateDataAdapter.notifyItemInserted(stateList.size()-1);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        stateSearch.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        adapter = new ArrayAdapter<String>
                                (getContext(),R.layout.autocomplete_custom, stateNameList);
                        stateSearch.setThreshold(1);
                        stateSearch.setAdapter(adapter);
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
}
