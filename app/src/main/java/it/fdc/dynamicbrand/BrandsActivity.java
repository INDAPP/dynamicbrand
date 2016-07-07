package it.fdc.dynamicbrand;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BrandsActivity extends AppCompatActivity{
    private RecyclerView mRecycler;
    private ArrayList<Integer> mLogo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new GridLayoutManager(this,2 ));
        mRecycler.setAdapter(new BrandAdapter(companyList(), this));
    }


    public class Data implements Serializable {
        public String brandName;
        public String brandDescription;
        public String brandDetails;
        public Drawable brandLogo;

        Data(String title, String description, Drawable imageId, String details) {
            this.brandName = title;
            this.brandDescription = description;
            this.brandLogo = imageId;
            this.brandDetails = details;
        }

    }

    public class BrandAdapter extends RecyclerView.Adapter<BrandCell> {

        List<Data> list = Collections.emptyList();
        Context context;

        public BrandAdapter(List<Data> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public BrandCell onCreateViewHolder(ViewGroup parent, int viewType) {
            //Inflate the layout, initialize the View Holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_brand, parent, false);
            BrandCell holder = new BrandCell(v);
            return holder;

        }

        @Override
        public void onBindViewHolder(BrandCell holder, int position) {
            Data data = list.get(position);
            holder.setupCell(data);


        }

        @Override
        public int getItemCount() {
            //returns the number of elements the RecyclerView will display
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }




    }

    public class BrandCell extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView brandName;
        //TextView description;
        ImageView brandLogo;
        Data data;

        BrandCell(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            brandName = (TextView) itemView.findViewById(R.id.name_company);
            //description = (TextView) itemView.findViewById(R.id.description);
            brandLogo = (ImageView) itemView.findViewById(R.id.logo_company);

            itemView.setOnClickListener(this);
        }

        public void setupCell(Data data) {
            //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
            this.brandName.setText(data.brandName);
            //holder.description.setText(list.get(position).description);
            this.brandLogo.setImageDrawable(data.brandLogo);
            this.data = data;

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(BrandsActivity.this, InfoActivity.class);
//            Bundle extras = new Bundle();
//
//            extras.putSerializable("value",data);
//            intent.putExtras(extras);
            startActivity(intent);

        }
    }

    public List<Data> companyList() {
        String[] companyName = getResources().getStringArray(R.array.company_name);
        String[] companyDescription = getResources().getStringArray(R.array.company_description);
        String[] companyUrlDetails = getResources().getStringArray(R.array.url_company_details);
        TypedArray companyLogos = getResources().obtainTypedArray(R.array.company_logo);



        List<Data> data = new ArrayList<>();
        for(int i=0; i<companyName.length; i++) {
            Drawable logo = companyLogos.getDrawable(i);
            data.add(new Data(companyName[i], companyDescription[i], logo ,companyUrlDetails[i]));

          }
            return data;
        }
}



