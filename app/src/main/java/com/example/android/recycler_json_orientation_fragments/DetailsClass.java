package com.example.android.recycler_json_orientation_fragments;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsClass extends Fragment {
    //ArrayList<String> val = new ArrayList<>();
    //JsonObject jsonObject = new JsonObject();
    JSO jsonObject = new JSO();
    TextView textView,textView1,textView2;
    ImageView imageView;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    Toolbar toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Log.e("Position: ",val.get(0));
        //Log.e("Title: ",val.get(1));
        //Log.e("Description: ",val.get(2));
        //Log.e("Ratings: ",val.get(3));

        //textView = (TextView)view.findViewById(R.id.title_detail);
        //textView1 = (TextView)view.findViewById(R.id.rating_detail);
        //textView2 = (TextView)view.findViewById(R.id.des_detail);
        //imageView = (ImageView)view.findViewById(R.id.image_detail);
        //textView2.setText(val.get(2));
        //textView1.setText(val.get(3));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu,menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.close)
        {
            int fragCount = getFragmentManager().getBackStackEntryCount();
            for (int i = 0; i < fragCount; i++)
            {
                getFragmentManager().popBackStack();
            }
            return true;
        }
        else if (item.getItemId() == android.R.id.home)
        {
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        jsonObject = (JSO) bundle.getSerializable("Object");
        View view = inflater.inflate(R.layout.details,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setBackground(new BitmapDrawable(jsonObject.getBackdrop()));

        relativeLayout = view.findViewById(R.id.recyclerClicker);

        /*relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();
            }
        });*/

        Adapter2 adapter2 = new Adapter2();
        recyclerView.setAdapter(adapter2);

        setHasOptionsMenu(true);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        toolbar.setTitle("");

        return view;
    }


    public class Adapter2 extends RecyclerView.Adapter<ViewHolderClass1>{
        @NonNull
        @Override
        public ViewHolderClass1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if (viewType == 1)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagelayout,parent,false);
                return new ImageHolder(view);
            }
            else if (viewType == 2)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textlayout1,parent,false);
                return new Text1Holder(view);
            }
            else if (viewType == 3)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textlayout2,parent,false);
                return new Text2Holder(view);
            }
            else
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textlayout2,parent,false);
                return new Text2Holder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass1 holder, int position) {

            if (position == 0)
            {
                //byte [] encodeByte= Base64.decode(jsonObject.poster.get(position),Base64.DEFAULT);
                //imageView.setImageBitmap(BitmapFactory.decodeFile(val.get(4)));
                ((ImageHolder)holder).imageView.setImageBitmap(jsonObject.getPost());
            }
            else if (position == 1)
            {
                ((Text1Holder)holder).textView.setText(jsonObject.getmTitle());
            }
            else if (position == 2)
            {
                ((Text2Holder)holder).textView.setText("Ratings: " + jsonObject.getRating());
            }
            else
            {
                ((Text2Holder)holder).textView.setText(jsonObject.getOver());
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 1;
            else if (position == 1)
                return 2;
            else if (position == 2)
                return 3;
            else
                return 4;
        }
    }

    public class ViewHolderClass1 extends RecyclerView.ViewHolder{
        public ViewHolderClass1(View itemView) {
            super(itemView);
        }
    }

    public class ImageHolder extends ViewHolderClass1{
        ImageView imageView;
        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_detail);
        }
    }

    public class Text1Holder extends ViewHolderClass1{
        TextView textView;
        public Text1Holder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.title_detail);
        }
    }

    public class Text2Holder extends ViewHolderClass1{
        TextView textView;
        public Text2Holder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.des_detail);
        }
    }
}
