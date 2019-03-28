package com.example.kazajii.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class  PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.MyViewHolder> implements Filterable {

    List<Pharmacie> mPharmacieList;
    List<Pharmacie> mPharmacieListfull;
    Context mContext;

    public PharmacieAdapter(List<Pharmacie> pharmacieList, Context context) {
        mContext = context;
        mPharmacieList = pharmacieList;
        mContext = context;
        mPharmacieListfull = new ArrayList<>(mPharmacieList);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.pharmacie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.description.setText(mPharmacieList.get(position).Description);
        holder.nom.setText(mPharmacieList.get(position).Name);
        holder.mImageView.setImageResource(mPharmacieList.get(position).Image);
        holder.ville.setText(mPharmacieList.get(position).Ville);
        holder.quartier.setText(mPharmacieList.get(position).Quartier);
    }

    @Override
    public int getItemCount() {
        return mPharmacieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView nom;
        private TextView description;
        private  TextView ville;
        private  TextView quartier;


        public MyViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.pharmacie_item_img);
            nom = itemView.findViewById(R.id.pharmacie_item_name);
            description = itemView.findViewById(R.id.pharmacie_item_desc);
            ville = itemView.findViewById(R.id.pharmacie_item_ville);
            quartier=itemView.findViewById(R.id.pharmacie_item_quartier);

        }
    }

    @Override
    public Filter getFilter() {
        return mPharmacieListFilter;
    }

    private Filter mPharmacieListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pharmacie>  filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mPharmacieListfull);
            }else{
                String filterPatern = constraint.toString().toLowerCase().trim();

                for(Pharmacie item : mPharmacieListfull){
                    if (item.Name.toLowerCase().contains(filterPatern) ||item.Ville.toLowerCase().contains(filterPatern)|| item.Quartier.toLowerCase().contains(filterPatern) ){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;

        }

        @Override
        protected void publishResults(CharSequence contraint, FilterResults filterResults) {
            mPharmacieList.clear();
            mPharmacieList.addAll( (List)filterResults.values);
            notifyDataSetChanged();

        }
    };
}