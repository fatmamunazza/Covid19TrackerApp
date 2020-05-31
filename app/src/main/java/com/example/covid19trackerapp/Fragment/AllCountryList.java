package com.example.covid19trackerapp.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import com.example.covid19trackerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllCountryList extends Fragment {

    public AllCountryList() {
        // Required empty public constructor
    }
    private RequestQueue requestQueue;
    private ArrayList<CountryData> countryList;
    private CountryDataAdapter countryDataAdapter;

    private String affectedCountries;
    ProgressBar progressBar;

  //  private SwipeRefreshLayout pullToRefresh;

    private RecyclerView recyclerView;
    private LinearLayout homeList;

    private AutoCompleteTextView countrySearch;

    private String[] countryNameList;
    private String[] mList=new String[]{"afaqyugs" ,"bjabdj","asddd","asdfgh","",""};
    private  ArrayAdapter<String> adapter;
    private CardView listOfCountry;
    private LinearLayout countryDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_country_list, container, false);


      //  pullToRefresh=view.findViewById(R.id.pullToRefresh);
        progressBar=view.findViewById(R.id.progressBar);

        recyclerView=view.findViewById(R.id.recyclerView);

        countrySearch=view.findViewById(R.id.countrySearch);

        listOfCountry=view.findViewById(R.id.listOfCountry);

        countryDetails=view.findViewById(R.id.containerCountry);

        countrySearch.setEnabled(false);

        countryList=new ArrayList<>();

        countryDataAdapter = new CountryDataAdapter(getContext(), countryList, recyclerView);

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

        recyclerView.setAdapter(countryDataAdapter);

        countrySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                listOfCountry.setVisibility(View.GONE);
                countryDetails.setVisibility(View.VISIBLE);
                String str= ((TextView)view).getText().toString();
                Fragment fr=new Country();
                Bundle bundle=new Bundle();
                bundle.putString("screenName","Other");
                bundle.putString("country",str);
                fr.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.containerCountry,fr).commit();
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
        String url2 = "https://disease.sh/v2/countries";
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
                        countryNameList =new String[length];

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

                                countryNameList[i]=obj.getString("country");
                                countryList.add(countryData);

                                countryDataAdapter.notifyItemInserted(countryList.size()-1);
                                countrySearch.setEnabled(true);
                            }

                            progressBar.setVisibility(View.GONE);
                            if(getContext()!=null) {
                                adapter = new ArrayAdapter<String>
                                        (getContext(), R.layout.autocomplete_custom, countryNameList);
                                countrySearch.setThreshold(1);
                                countrySearch.setAdapter(adapter);
                            }
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

                    }
                });
        requestQueue.add(jsonArrayRequest);


    }
}
