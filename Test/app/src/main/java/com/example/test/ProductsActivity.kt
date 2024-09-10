package com.example.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ActivityProductsBinding
import com.example.test.interfaces.CategoryInterface
import com.example.test.interfaces.ProductInterface
import com.example.test.models.Product
import com.example.test.models.ProductCategory
import com.example.test.utils.CategorySelection

class ProductsActivity : AppCompatActivity(), ProductInterface {

    private var columnCount = 2
    private var productCategoriesList = mutableListOf<Product>()
    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryJeans = Product()
        //categoryJeans.categoryId = 10
        //categoryJeans.categoryName = "Jeans"
        //categoryJeans.categoryThumbnailImage = R.drawable.female_jeans
        productCategoriesList.add(categoryJeans)

        val categoryTshirts = Product()
        //categoryTshirts.categoryId = 11
        //categoryTshirts.categoryName = "T-Shirts"
        //categoryTshirts.categoryThumbnailImage = R.drawable.female_t_shirts
        productCategoriesList.add(categoryTshirts)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        //val view = inflater.inflate(R.layout.fragment_female_list, container, false)
        setContentView(binding.root)

        // Set the adapter
        if (true) {
            with(binding.root) {
               binding.productsList.layoutManager = GridLayoutManager(context, 2)
                binding.productsList.adapter = ProductItemRecyclerView( this@ProductsActivity, CategorySelection.selectedCategoryForMale.productsList)
            }
        }
    }

    override fun selectedProduct(position: Int, selectedCategory: Product) {

        //TODO differentiate between male and female product
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("${selectedCategory.productName} has been added for trial")
        builder.setPositiveButton("Ok", null)
        builder.show()
        //Toast.makeText(this.baseContext, "Clicked on  for female ", Toast.LENGTH_SHORT).show()
    }

}