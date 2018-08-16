package com.example.android.recycler_json_orientation_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class RecyclerFragment extends Fragment {

    RecyclerView recyclerView;

    static int mRand = 0;
    Random random = new Random();
    ArrayList<JSO> jsos = new ArrayList<>();
    MyAdapter myAdapter = new MyAdapter();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyfrag,container,false);


        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        if (savedInstanceState == null) {
            try {
                jsos = new TestAsync().execute().get();
            }
            catch (Exception e){}
            recyclerView.setAdapter(myAdapter);
        }
        else
        {
            jsos = (ArrayList<JSO>) savedInstanceState.getSerializable("Object");
            recyclerView.setAdapter(myAdapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolderClass>{
        @NonNull
        @Override
        public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_layout,
                        parent, false);
                return new MainViewHolder(view);
            }
            else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_view,
                        parent,false);
                return new SecondViewHolder(view);
            }
        }

        @Override
        public int getItemViewType(int position) {
            mRand = random.nextInt(99);
            if ((position+mRand)%2 == 0)
                return 1;
            else
                return 2;
        }

        @Override
        public int getItemCount() {
            return jsos.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {

            if ((position+mRand)%2 == 0) {
                ((MainViewHolder)holder).titleText.setText(jsos.get(position).getmTitle());
                ((MainViewHolder)holder).descText.setText(jsos.get(position).getOver());
                ((MainViewHolder)holder).itemNum.setText("Ratings: "+ jsos.get(position).getRating());
                //((MainViewHolder)holder).itemNum.setText(String.valueOf(position + 1));
                //String str = "ic_" + String.valueOf(position);
                //((MainViewHolder)holder).imageView.setImageDrawable(getDrawable(getResources().
                //       getIdentifier(str,"drawable",getPackageName())));
                ((MainViewHolder)holder).imageView.setImageBitmap(jsos.get(position).getPost());
            }
            else {
                ((SecondViewHolder)holder).titleText.setText(jsos.get(position).getmTitle());
                ((SecondViewHolder)holder).descText.setText(jsos.get(position).getOver());
                ((SecondViewHolder)holder).itemNum.setText("Ratings: "+ jsos.get(position).getRating());
                ((SecondViewHolder)holder).imageView.setImageBitmap(jsos.get(position).getPost());
            }
        }
    }

    public class MainViewHolder extends ViewHolderClass{
        TextView titleText, descText, itemNum;
        ImageView imageView;
        public MainViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView)itemView.findViewById(R.id.title_text);
            descText = (TextView)itemView.findViewById(R.id.description);
            itemNum = (TextView)itemView.findViewById(R.id.num);
            imageView = (ImageView)itemView.findViewById(R.id.image_view);
        }
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        public ViewHolderClass(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this,String.valueOf(getAdapterPosition()+1),
                    //        Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(),jObject.Movie_Title.get(getAdapterPosition()).get("Title"),Toast.LENGTH_SHORT).show();
                    //ArrayList <String> data = new ArrayList<>();
                    //data.add(Integer.toString(getAdapterPosition()));
                    //data.add(jObject.Movie_Title.get(getAdapterPosition()).get("Title"));
                    //data.add(jObject.Movie_Title.get(getAdapterPosition()).get("Description"));
                    //data.add(jObject.Movie_Title.get(getAdapterPosition()).get("Ratings"));
                    //ByteArrayOutputStream byteArrayOutputStream=new  ByteArrayOutputStream();
                    //jObject.poster.get(getAdapterPosition()).compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
                    //byte [] b=byteArrayOutputStream.toByteArray();
                    //String temp= Base64.encodeToString(b, Base64.DEFAULT);
                    //data.add(temp);
                    //jObject.pos = getAdapterPosition();
                    Bundle bundle = new Bundle();
                    //bundle.putStringArrayList("Clicked",data);
                    //bundle.putSerializable("Object",jObject);
                    bundle.putSerializable("Object",jsos.get(getAdapterPosition()));
                    Fragment fragment = new DetailsClass();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.recyclerFrag,fragment).addToBackStack("Here");
                    fragmentTransaction.commit();
                }
            });
        }
    }

    public class SecondViewHolder extends ViewHolderClass{
        TextView titleText, descText, itemNum;
        ImageView imageView;
        public SecondViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView)itemView.findViewById(R.id.title_text1);
            descText = (TextView)itemView.findViewById(R.id.description1);
            itemNum = (TextView)itemView.findViewById(R.id.num1);
            imageView = (ImageView)itemView.findViewById(R.id.image_view1);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("Object",jsos);
        super.onSaveInstanceState(outState);
    }

}
