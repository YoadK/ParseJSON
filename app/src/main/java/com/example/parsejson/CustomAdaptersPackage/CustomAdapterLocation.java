package com.example.parsejson.CustomAdaptersPackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.parsejson.ModelsPackage.Result;
import com.example.parsejson.OtherPackage.ConApp;
import com.example.parsejson.PagesPackage.FavoritesActivity;
import com.example.parsejson.PagesPackage.MapActivity;
import com.example.parsejson.R;
import com.example.parsejson.ViewModelPackage.PlaceViewModelFavorites;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterLocation extends RecyclerView.Adapter<CustomAdapterLocation.LocationViewHolder> {

    static class LocationViewHolder extends RecyclerView.ViewHolder {

        private TextView name1, address1;
        private LinearLayout linear1;

        private LocationViewHolder(View itemView) {
            super(itemView);

            name1 = itemView.findViewById(R.id.name1);
            address1 = itemView.findViewById(R.id.address1);
            linear1 = itemView.findViewById(R.id.linear1);
        }
    }

    private final LayoutInflater mInflater;
    private List<Result> mPlacesSearchList;
    private PlaceViewModelFavorites placeViewModelFavorites;

    public CustomAdapterLocation(Context context, ArrayList<Result> dataList) {
        mInflater = LayoutInflater.from(context);
        this.mPlacesSearchList = dataList;
        placeViewModelFavorites = new PlaceViewModelFavorites(ConApp.getApplication());
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.location_item_row, parent, false);
        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocationViewHolder holder, final int position) {
        if (mPlacesSearchList != null) {
            final Result current = mPlacesSearchList.get(position);
            holder.name1.setText(current.getName());
            holder.address1.setText(current.getVicinity());
            try {
                Glide.with(mInflater.getContext()).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="
                        + current.getPhotos().get(0).getPhoto_reference() +
                        "&key=" + mInflater.getContext().getString(R.string.api_key))
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NotNull Drawable resource, Transition<? super Drawable> transition) {
                                holder.linear1.setBackground(resource);
                            }
                        });
            } catch (Exception e) {

            }

            holder.linear1.setOnClickListener(view -> {
                Intent intent = new Intent(mInflater.getContext(), MapActivity.class);
                intent.putExtra(mInflater.getContext().getString(R.string.map_data), current);
                mInflater.getContext().startActivity(intent);
            });

            holder.linear1.setOnLongClickListener(view -> {
                placeViewModelFavorites.insertPlace(current.getName(), current.getVicinity(),
                        current.getGeometry().getLocation().getLat(), current.getGeometry().getLocation().getLng(),
                        current.getPhotos().get(0).getPhoto_reference());

                Intent intent = new Intent(mInflater.getContext(), FavoritesActivity.class);
                mInflater.getContext().startActivity(intent);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mPlacesSearchList != null)
            return mPlacesSearchList.size();
        else return 0;
    }

}
