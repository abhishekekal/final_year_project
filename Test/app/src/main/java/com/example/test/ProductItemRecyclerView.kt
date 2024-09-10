package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.interfaces.CategoryInterface
import com.example.test.interfaces.ProductInterface
import com.example.test.models.Product

class ProductItemRecyclerView(private val productInterface : ProductInterface, private val items: List<Product>
) : RecyclerView.Adapter<ProductItemRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemRecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentView.text = items.get(position).productName
        holder.idView.setImageResource(items.get(position).productThumbnailImage)
        holder.itemView.setOnClickListener {
            productInterface.selectedProduct(position, items.get(position))
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: ImageView = itemView.findViewById(R.id.idIVcourseIV)
        val contentView: TextView = itemView.findViewById(R.id.idProductName)
    }

}