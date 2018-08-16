package com.example.android.recycler_json_orientation_fragments;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonObject extends AppCompatActivity implements Serializable{

    ArrayList<HashMap<String,String>> Movie_Title = new ArrayList<>();
    ArrayList<Bitmap> poster = new ArrayList<>();
    int pos = 0;

     public JsonObject(){}

     /*public void display(){
         for (int i = 0; i < Movie_Title.size(); i++)
         {
             Log.e("Title: ",Movie_Title.get(i).get("Title"));
             Log.e("Description: ",Movie_Title.get(i).get("Description"));
             Log.e("Ratings: ",Movie_Title.get(i).get("Ratings"));
         }
     }*/
}
