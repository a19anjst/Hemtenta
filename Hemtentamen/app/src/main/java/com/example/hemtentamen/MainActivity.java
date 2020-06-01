package com.example.hemtentamen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19anjst_2");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        private ArrayList<String> FishNames=new ArrayList<String>();
        private ArrayList<String> FishLocs=new ArrayList<String>();
        private ArrayList<String> FishSize=new ArrayList<String>();
        private ArrayList<String> FishPrefs=new ArrayList<String>();



        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a19anjst_2");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {

            try{
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String location = jsonObject.getString("location");
                    String company = jsonObject.getString("company");
                    String category = jsonObject.getString("category");

                    FishNames.add(name);
                    FishLocs.add(location);
                    FishSize.add(company);
                    FishPrefs.add(category);
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, R.layout.list_textview, R.id.list_TEXTVIEW, FishNames);
                ListView my_listview=(ListView) findViewById(R.id.list_LISTVIEW);
                my_listview.setAdapter(adapter);
                my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("names", FishNames.get(i));
                        intent.putExtra("size", FishSize.get(i));
                        intent.putExtra("location", FishLocs.get(i));
                        intent.putExtra("prefs", FishPrefs.get(i));
                        startActivity(intent);
                    }
                });
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
