package com.example.restcountries;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button fetchButton;
    ProgressDialog pd;
    FetchJSON fetchJSON;
    StringBuffer response;
    String responseText;
    ArrayList<Model> countriesObj = new ArrayList();
    ListView listView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchButton = (Button) findViewById(R.id.fetchContent);
        listView = (ListView) findViewById(R.id.listView);
        title = (TextView) findViewById(R.id.listTitle);
        fetchButton.setOnClickListener(this);

        fetchJSON = new FetchJSON();

    }

    protected void downloadJSON(String... urls) {

        URL url;
        HttpURLConnection conn;
        String result = "";

        try {

            url = new URL(urls[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            if(responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output;
                response = new StringBuffer();

                while ((output = in.readLine()) != null) {

                    response.append(output);

                }

                in.close();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        responseText = response.toString();

        try {

            JSONArray jsonarray = new JSONArray(responseText);

            for(int i = 0; i < jsonarray.length(); i++) {

                JSONObject jsonObject = jsonarray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String capital = jsonObject.getString("capital");
                String flag = jsonObject.getString("flag");
                String region = jsonObject.getString("region");
                String subRegion = jsonObject.getString("subregion");
                String population = jsonObject.getString("population");
                Log.i("Data: ", (name));
                String bordersArray = jsonObject.getString("borders");
                String languagesArray = jsonObject.getString("languages");
                JSONArray jsonArrayBorder = new JSONArray(bordersArray);
                JSONArray jsonArrayLang = new JSONArray(languagesArray);
                ArrayList<String> languages = new ArrayList<>();
                ArrayList<String> borders = new ArrayList<>();

                for(int j = 0; j < jsonArrayLang.length(); j++) {

                    JSONObject jsonLangObject = jsonArrayLang.getJSONObject(j);
                    languages.add(jsonLangObject.getString("name"));

                }

                for(int j = 0; j < jsonArrayBorder.length(); j++) {

                    borders.add(jsonArrayBorder.getString(j));

                }

                Model model = new Model(name, capital, flag, region, subRegion, population, borders, languages);
                countriesObj.add(model);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    class FetchJSON extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected String doInBackground(String... urls) {

            downloadJSON(urls);
            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            if (pd.isShowing()) {

                fetchButton.setVisibility(View.GONE);
                title.setVisibility(View.VISIBLE);
                pd.dismiss();

            }

            ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this, countriesObj);
            listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String message = "Country Name: " + countriesObj.get(position).getCountryName() + "\n"
                            + "Capital Name: " + countriesObj.get(position).getCapital() + "\n"
                            + "Flag Url: " + countriesObj.get(position).getFlag() + "\n"
                            + "Region Name: " + countriesObj.get(position).getRegion() + "\n"
                            + "Sub Region Name: " + countriesObj.get(position).getSubRegion() + "\n"
                            + "Total Population: " + countriesObj.get(position).getPopulation() + "\n"
                            + "Borders Code: " + countriesObj.get(position).getBorders() + "\n"
                            + "Languages: " + countriesObj.get(position).getLanguages() + "\n";

                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    Log.i("Flag URL", countriesObj.get(position).getFlag());

                }
            });

        }
    }

    @Override
    public void onClick(View v) {

        try {

            fetchJSON.execute("https://restcountries.eu/rest/v2/region/asia/");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}