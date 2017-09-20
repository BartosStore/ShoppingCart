package com.miba.shoppingcart.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miba.shoppingcart.KApp
import com.miba.shoppingcart.entities.ShoppingItem
import kotlinx.android.synthetic.main.list_simple.view.*

/**
 * Created by miba on 20.09.2017.
 */
class ShopListAdapter(
        private val context: Context,
        private var goods: ArrayList<ShoppingItem>,
        private val layout: Int) : RecyclerView.Adapter<ShopListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(goods.get(position))

    override fun getItemCount() = goods.size

    fun setData(goods: ArrayList<ShoppingItem>) {
        this.goods = goods
        notifyDataSetChanged()
        Log.d(KApp.LOG_MIBA, "ShopListAdapter -> goods: " + goods.toString())
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(shoppingItem: ShoppingItem) {
            itemView.tvShopitemTitle.text = shoppingItem.name
            itemView.tvShopitemDescription.text = shoppingItem.description
        }
    }
}