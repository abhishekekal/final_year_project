package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.FragmentMaleBinding
import com.example.test.interfaces.CategoryInterface
import com.example.test.models.ProductCategory
import com.example.test.placeholder.PlaceholderContent.PlaceholderItem


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemRecyclerViewAdapter(
    private val context: Context, private val isForMale:Boolean, private val categoryInterface : CategoryInterface, private val items: List<ProductCategory>
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_view, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentView.text = items.get(position).categoryName
        holder.idView.setImageResource(items.get(position).categoryThumbnailImage)
        if (isForMale) {
            holder.itemView.setOnClickListener {
                //Toast.makeText(context, "Clicked at category ${values.get(position).id} for male ", Toast.LENGTH_SHORT).show()
                categoryInterface.selectedCategory(position, items.get(position))
            }
        }
        else {
            holder.itemView.setOnClickListener {
                //Toast.makeText(context, "Clicked at category ${position} for female ", Toast.LENGTH_SHORT).show()
                categoryInterface.selectedCategory(position, items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isForMale) {
            items.count()
        } else {
            items.count()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: ImageView = itemView.findViewById(R.id.idIVcourseIV)
        val contentView: TextView = itemView.findViewById(R.id.idTVCourse)

        //override fun toString(): String {
          //  return super.toString() + " '" + contentView.text + "'"
       // }
    }

}