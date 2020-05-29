package com.example.covid19trackerapp;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequest {

   /* public static void jsonObjectRequest(final Map<Object, Object> requestMap, final VolleyCallback callback) {

      *//*  if (!Common.isNetworkAvailable((Context) requestMap.get("context"))) {
            return;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Integer.parseInt(requestMap.get("method").toString()), requestMap.get("url").toString(),
                        (JSONObject) requestMap.get("jsonBody"), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessJsonObject(response);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                        callback.onErrorJsonObject(error);

                    }
                }) {//This is for Headers If You Needed

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");
                params.put(cookieStr, session_id);
                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                timeout,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Access the RequestQueue through your singleton class.
        VolleyConfiguration.getInstance((Context) requestMap.get("context")).addToRequestQueue(jsonObjectRequest);*//*
    }*/


}
