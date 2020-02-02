package com.shakib.retrofittest.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shakib.retrofittest.Common.categoryName
import com.shakib.retrofittest.R
import com.shakib.retrofittest.doodle.Category
import kotlinx.android.synthetic.main.row_doodle.view.*

class DoodleAdapterKotlin(val mContext: Context, private val mCategoryList: List<Category>): RecyclerView.Adapter<DoodleAdapterKotlin.DoodleViewHolderKotlin>() {

    inner class DoodleViewHolderKotlin(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentCategory: Category? = null
        init {
            itemView.setOnClickListener {
                Toast.makeText(mContext, currentCategory!!.category_name, Toast.LENGTH_LONG).show()
            }
        }
        fun setData(mCategory: Category){
            itemView.category_name.text = mCategory.category_name
            itemView.category_btn.setOnClickListener {
                categoryName.add(mCategory.category_name)
                Toast.makeText(mContext, mCategory.category_name+" ADDED", Toast.LENGTH_LONG).show()
            }
            this.currentCategory = mCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleViewHolderKotlin {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_doodle, parent, false)
        return DoodleViewHolderKotlin(view)
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    override fun onBindViewHolder(holder: DoodleViewHolderKotlin, position: Int) {
        val mCategory = mCategoryList[position]
        holder.setData(mCategory)
    }
}