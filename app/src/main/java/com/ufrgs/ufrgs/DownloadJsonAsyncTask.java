package com.ufrgs.ufrgs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<String>> {

        ProgressDialog dialog;

        @Override
        protected List<String> doInBackground(String... params) {
            String urlString = params[0];

            HttpClient httpclient = new DefaultHttpClient() {
            };//new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);

            try {
                HttpResponse response = httpclient.execute(httpget);

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream instream = entity.getContent();

                    String json = instream.toString(); // estava função toString(ins...)
                    instream.close();

                    List<String> trends = this.getTrends(json);
                    Log.w("TESTE", trends.get(0));
                    return trends;
                }
            } catch (Exception e) {
                Log.e("DEVMEDIA", "Falha ao acessar Web service", e);
            }
            return null;

        }

        private List<String> getTrends(String jsonString) {

            List<String> trends = new ArrayList<String>();
/*
            try {
                JSONArray trendLists = new JSONArray(jsonString);
                JSONObject trendList = trendLists.getJSONObject(0);
                JSONArray trendsArray = trendList.getJSONArray("trends");
                trends.add(trendList.toString());
                return trends;
/*
                JSONObject trend;

                for (int i = 0; i < trendsArray.length(); i++) {
                    trend = new JSONObject(trendsArray.getString(i));

                    Log.i("DEVMEDIA", "nome=" + trend.getString("name"));

                    String objetoTrend = new String();
                    objetoTrend.name = trend.getString("name");
                    objetoTrend.url = trend.getString("url");

                    trends.add(objetoTrend);
                }
            } catch (JSONException e) {
                trends.add(jsonString);
                Log.e("DEVMEDIA", "Erro no parsing do JSON", e);
            }*/
            trends.add(jsonString);
            return trends;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(null, "Aguarde",
                    "Baixando JSON, Por Favor Aguarde...");
        }

    }