package com.example.android.recycler_json_orientation_fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

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

public class TestAsync extends AsyncTask<Void, Void, ArrayList<JSO>>{

    ArrayList<JSO> jsos = new ArrayList<>();

    @Override
    protected ArrayList<JSO> doInBackground(Void... params) {
        StringBuffer response = null;

        //1 step create a URL object
        URL url = null;
        try {
            url = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpsURLConnection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            JSONObject jsonObject = new JSONObject(response.toString());

            JSONArray Results = jsonObject.getJSONArray("results");

            for (int i = 0; i < Results.length(); i++)
            {
                JSONObject jsonObject1 = Results.getJSONObject(i);

                JSO jso = new JSO();
                ArrayList<String> gen = new ArrayList<>();

                jso.setmTitle(jsonObject1.getString("title"));
                //jso.setAdult(jsonObject.getString("adult"));
                jso.setBack("https://image.tmdb.org/t/p/w500/" + jsonObject1.getString("backdrop_path"));
                //jso.setLang(jsonObject1.getString("original_language"));
                jso.setOver(jsonObject1.getString("overview"));
                jso.setPoster("https://image.tmdb.org/t/p/w500/" + jsonObject1.getString("poster_path"));
                jso.setRating(jsonObject1.getString("vote_average"));
                //jso.setrDate(jsonObject1.getString("release_date"));
                //JSONArray genr = jsonObject1.getJSONArray("genre_ids");

                    /*for (int j = 0; j < genr.length(); j++)
                    {
                        gen.add(genr.getString(i));
                    }
                    jso.setGenre(gen);*/

                //String mTitle = jsonObject1.getString("title");
                //String mDescr = jsonObject1.getString("overview");
                //String mRate = jsonObject1.getString("vote_average");
                //String mImage = "https://image.tmdb.org/t/p/w500/" +
                //        jsonObject1.getString("poster_path");
                //Log.e("Title",mTitle);
                //Log.e("Description", mDescr);
                //Log.e("Rating", mRate);
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream)new URL(jso.getPoster()).getContent());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap bitmap1 = null;
                try {
                    bitmap1 = BitmapFactory.decodeStream((InputStream)new URL(jso.getBack()).getContent());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                jso.setPost(bitmap);
                jso.setBackdrop(bitmap1);

                //HashMap<String,String> movie = new HashMap<>();
                //movie.put("Title",mTitle);
                //movie.put("Description",mDescr);
                //movie.put("Ratings",mRate);
                //movie.put("Image",mImage);

                //jObject.Movie_Title.add(movie);
                //jObject.poster.add(bitmap);

                jsos.add(jso);
                //myAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsos;
    }

    @Override
    protected void onPostExecute(ArrayList<JSO> jsos) {
        super.onPostExecute(jsos);
    }
}