package com.shakib.retrofittest.helpers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shakib.retrofittest.R;
import com.shakib.retrofittest.dota.Dota2Hero;
import java.util.List;

public class DotaAdapter extends RecyclerView.Adapter<DotaAdapter.HeroViewHolder> {

    int selected_position = 0;
    private Context mContext;
    private List<Dota2Hero> mHeroList;

    public DotaAdapter(Context mContext, List<Dota2Hero> mHeroList) {
        this.mContext = mContext;
        this.mHeroList = mHeroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_dota, null);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, final int position) {
        final Dota2Hero hero = mHeroList.get(position);

        holder.cardView.setActivated(hero.isSelected());
        holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);

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

    class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name, role;
        CardView cardView;

        HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.main_card_view);
            imageView = itemView.findViewById(R.id.hero_image);
            name = itemView.findViewById(R.id.hero_name);
            role = itemView.findViewById(R.id.hero_role);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            // Do your another stuff for your onClick
        }
    }
}
