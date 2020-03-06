package com.shakib.retrofittest.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shakib.retrofittest.R;
import com.shakib.retrofittest.doodle.Category;

import java.util.List;

public class DoodleAdapter extends RecyclerView.Adapter<DoodleAdapter.DoodleViewHolder> {

    private Context mContext;
    private List<Category> mCategoryList;

    public DoodleAdapter(Context mContext, List<Category> mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }

    static class DoodleViewHolder extends RecyclerView.ViewHolder {

        TextView mCategoryName;
        Button mCategoryButton;

        DoodleViewHolder(@NonNull View itemView) {
            super(itemView);
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryButton = itemView.findViewById(R.id.category_btn);
        }
    }

    @NonNull
    @Override
    public DoodleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_doodle, parent);
        return new DoodleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoodleViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);

        holder.mCategoryName.setText(category.getCategory_name());
        holder.mCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on"+category.getCategory_name(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
