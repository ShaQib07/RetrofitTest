package com.shakib.retrofittest.helpers;

/*
*RecyclerView>Adapter
* RecyclerView.ViewHolder
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shakib.retrofittest.R;
import com.shakib.retrofittest.dota.Dota2Hero;

import java.util.List;

public class RecyclerViewHeroAdapter extends RecyclerView.Adapter<RecyclerViewHeroAdapter.HeroViewHolder> {

    private Context mContext;
    private List<Dota2Hero> mHeroList;

    public RecyclerViewHeroAdapter(Context mContext, List<Dota2Hero> mHeroList) {
        this.mContext = mContext;
        this.mHeroList = mHeroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row, null);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Dota2Hero hero = mHeroList.get(position);

        holder.name.setText(hero.getName());
        holder.role.setText(hero.getRole());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);



        Glide.with(mContext).load(hero.getImage_url()).apply(options).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, role;

        HeroViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hero_image);
            name = itemView.findViewById(R.id.hero_name);
            role = itemView.findViewById(R.id.hero_role);
        }
    }
}
