package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.interfaces.ProductInterface
import com.example.test.models.Product

class CartItemRecyclerViewAdapter (private val productInterface : ProductInterface, private val items: List<Product>) : RecyclerView.Adapter<CartItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemRecyclerViewAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentView.text = items.get(position).productName
        holder.idView.setBackgroundResource(items.get(position).productThumbnailImage)
        holder.buttonDelete.setOnClickListener {
            productInterface.selectedProduct(position, items.get(position))
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: ImageView = itemView.findViewById(R.id.cart_product_image)
        val contentView: TextView = itemView.findViewById(R.id.idProductName)
        val buttonDelete: ImageButton = itemView.findViewById(R.id.button_delete)
    }

}